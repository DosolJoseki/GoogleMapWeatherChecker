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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.marker_view.view.*
import java.lang.StringBuilder


class MapMarkerAdapter(
    private val context: Context?,
    private val map: Map<Marker, WeatherInfo>
): GoogleMap.InfoWindowAdapter {

    companion object{
        private const val IMAGE_URL_FIRST = "http://openweathermap.org/img/wn/"
        private const val IMAGE_URL_SECOND = "@2x.png"
    }

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

        Picasso.get().load(buildPicUrl(weatherInfo.let {
            it!!.weather.first().icon
        })).into(view.imageView)

        return view
    }

    private fun buildPicUrl(image: String): String {
        return StringBuilder().append(IMAGE_URL_FIRST).append(image).append(IMAGE_URL_SECOND).toString()
    }
}