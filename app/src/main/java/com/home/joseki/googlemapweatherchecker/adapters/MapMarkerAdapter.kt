package com.home.joseki.googlemapweatherchecker.adapters

import android.content.Context
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import androidx.appcompat.view.ContextThemeWrapper
import com.home.joseki.googlemapweatherchecker.R
import com.home.joseki.googlemapweatherchecker.converters.Converter
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import kotlinx.android.synthetic.main.marker_view.view.*


class MapMarkerAdapter(
    private val context: Context?,
    private val map: Map<Marker, WeatherInfo>
): GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker?): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker?): View {
        val wrapper = ContextThemeWrapper(context, R.style.AppTheme)
        val inflater = wrapper.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.marker_view, null)

        val weatherInfo = map[marker]

        view.tvCityName.text = weatherInfo.let { it!!.name }
        view.tvTemp.text = weatherInfo.let { Converter.fahrenheitToCelsius(it!!.main.temp) }

        return view
    }
}