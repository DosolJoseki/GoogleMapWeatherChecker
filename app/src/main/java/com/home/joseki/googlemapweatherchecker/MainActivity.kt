package com.home.joseki.googlemapweatherchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.home.joseki.googlemapweatherchecker.di.Scopes
import com.home.joseki.googlemapweatherchecker.di.navigation.MainRouter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: MainActivityPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scope = Toothpick.openScope(Scopes.APP)
        Toothpick.inject(this, scope)
        presenter = MainActivityPresenter(scope.getInstance(MainRouter::class.java))
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
}
