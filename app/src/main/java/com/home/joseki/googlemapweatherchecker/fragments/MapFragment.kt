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
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import toothpick.Toothpick
import com.home.joseki.googlemapweatherchecker.R
import com.google.android.gms.maps.model.*
import com.home.joseki.googlemapweatherchecker.adapters.MapMarkerAdapter
import com.home.joseki.googlemapweatherchecker.model.CityInfo

class MapFragment: Fragment(), OnMapReadyCallback {
    private lateinit var presenter: MapFragmentPresenter
    private lateinit var googleMap: GoogleMap
    var isInitialazed: Boolean = false

    companion object {
        @JvmStatic
        fun newInstance() = MapFragment()

        private const val ZOOM = 8f
        private const val DURATION_MS = 1000
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
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

    fun setWeatherPins(list: MutableList<WeatherInfo>){
        if(list.isNullOrEmpty())
            return

        googleMap.clear()

        val map = mutableMapOf<Marker, WeatherInfo>()

        for(e in list) {
            map[googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(e.coord.lat, e.coord.lon))
            )] = e
        }

        googleMap.setOnInfoWindowClickListener {
            presenter.onMarkerClick(map[it]!!)
        }

        googleMap.setInfoWindowAdapter(MapMarkerAdapter(context, map))
        isInitialazed = true
    }

    fun setCamPosition(cityInfo: CityInfo){
        val googlePlex = CameraPosition.builder()
            .target(LatLng(cityInfo.lat, cityInfo.lon))
            .zoom(ZOOM)
            .build()

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), DURATION_MS, null)
    }
}