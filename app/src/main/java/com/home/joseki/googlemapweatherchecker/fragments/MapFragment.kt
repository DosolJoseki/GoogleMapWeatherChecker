package com.home.joseki.googlemapweatherchecker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.home.joseki.googlemapweatherchecker.di.Scopes
import com.home.joseki.googlemapweatherchecker.di.navigation.MainRouter
import com.home.joseki.googlemapweatherchecker.interactors.IMapWeatherInteractor
import com.home.joseki.googlemapweatherchecker.model.CityList
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import toothpick.Toothpick
import com.home.joseki.googlemapweatherchecker.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.maps.model.*


class MapFragment: Fragment(), OnMapReadyCallback {
    private lateinit var presenter: MapFragmentPresenter
    private lateinit var googleMap: GoogleMap

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.clear()

        val googlePlex = CameraPosition.builder()
            .target(LatLng(27.1750, 78.0422))
            .zoom(8f)
            .build()

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 100, null);

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(27.1750, 78.0422))
                .title("Taj Mahal")
                .snippet("It is located in India")
                .rotation(3.5.toFloat())
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("cloud", 100, 100)))
        ).showInfoWindow()
    }

    private fun resizeMapIcons(iconName: String, width: Int, height: Int): Bitmap {
        val imageBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier(iconName, "mipmap", activity!!.packageName))
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        val mMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mMapFragment.getMapAsync(this)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scope = Toothpick.openScope(Scopes.APP)
        Toothpick.inject(this, scope)
        presenter = MapFragmentPresenter(
            this,
            scope.getInstance(MainRouter::class.java),
            scope.getInstance(IMapWeatherInteractor::class.java)
        )
    }

    fun setWeather(weatherInfo: WeatherInfo){
        if (weatherInfo == null) {
            val i = 0
        }
    }

    fun showUpdateProgress(boolean: Boolean){

    }

    fun setCity(cityList: CityList){
        if (cityList == null) {
            val i = 0
        }
    }
}