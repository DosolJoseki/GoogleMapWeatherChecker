package com.home.joseki.googlemapweatherchecker.fragments

import androidx.fragment.app.Fragment
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo

class WeatherInfoFragment(
    private val weatherInfo: WeatherInfo
): Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(weatherInfo: WeatherInfo) =
            WeatherInfoFragment(weatherInfo)
    }
}