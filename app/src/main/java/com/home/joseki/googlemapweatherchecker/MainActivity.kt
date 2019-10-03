package com.home.joseki.googlemapweatherchecker

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.home.joseki.googlemapweatherchecker.di.Scopes
import com.home.joseki.googlemapweatherchecker.di.navigation.MainRouter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick

import javax.inject.Inject
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter
    private var scope = Toothpick.openScope(Scopes.APP)

    companion object{
        private const val ACCESS_LOCATION_REQUEST = 0
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toothpick.inject(this, scope)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION ) == PackageManager.PERMISSION_GRANTED ) {
            presenter = MainActivityPresenter(scope.getInstance(MainRouter::class.java))
        } else {
            checkPermission()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.onBackPressed()
    }

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(this, R.id.fragmentLayout) {
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun checkPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        when (requestCode) {
            ACCESS_LOCATION_REQUEST -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    presenter = MainActivityPresenter(scope.getInstance(MainRouter::class.java))
                } else {
                    exitProcess(-1)
                }
                return
            }
        }
    }

}
