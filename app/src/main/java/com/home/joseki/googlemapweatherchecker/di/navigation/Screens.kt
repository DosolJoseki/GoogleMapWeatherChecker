package com.home.joseki.googlemapweatherchecker.di.navigation

import androidx.fragment.app.Fragment
import com.home.joseki.googlemapweatherchecker.fragments.MapFragment
import com.home.joseki.googlemapweatherchecker.fragments.WeatherForecastFragment
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {
    object MapScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = MapFragment.newInstance()
    }

    data class WeatherInfoScreen(val weatherInfo: WeatherInfo) : SupportAppScreen() {
        override fun getFragment(): Fragment = WeatherForecastFragment.newInstance(weatherInfo)
    }
}