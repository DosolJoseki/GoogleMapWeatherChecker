package com.home.joseki.googlemapweatherchecker.adapters

import android.content.Context
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import com.home.joseki.googlemapweatherchecker.R
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo


class MapMarkerAdapter(
    private val context: Context?,
    private val weatherInfo: WeatherInfo
): GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(p0: Marker?): View? {
        return null
    }

    override fun getInfoWindow(p0: Marker?): View {
        val wrapper = ContextThemeWrapper(context, R.style.AppTheme)
        val inflater = wrapper.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.marker_view, null)

        val cityName = view.findViewById<TextView>(R.id.tvCityName)
        val temp = view.findViewById<TextView>(R.id.tvTemp)

        temp.text = weatherInfo.main.temp
        cityName.text = weatherInfo.name

        return view
    }
}