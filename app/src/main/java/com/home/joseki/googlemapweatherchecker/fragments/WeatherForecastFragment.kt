package com.home.joseki.googlemapweatherchecker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.home.joseki.googlemapweatherchecker.R
import com.home.joseki.googlemapweatherchecker.di.Scopes
import com.home.joseki.googlemapweatherchecker.di.navigation.MainRouter
import com.home.joseki.googlemapweatherchecker.interactors.IMapWeatherInteractor
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import kotlinx.android.synthetic.main.fragment_weather_forecast.*
import toothpick.Toothpick

class WeatherForecastFragment(
    private val weatherInfo: WeatherInfo
): Fragment() {

    private lateinit var presenter: WeatherForecastFragmentPresenter

    companion object {
        @JvmStatic
        fun newInstance(weatherInfo: WeatherInfo) =
            WeatherForecastFragment(weatherInfo)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scope = Toothpick.openScope(Scopes.APP)
        Toothpick.inject(this, scope)
        presenter = WeatherForecastFragmentPresenter(
            this,
            scope.getInstance(MainRouter::class.java),
            scope.getInstance(IMapWeatherInteractor::class.java),
            weatherInfo
        )
    }

    fun fillForecast(forecast: Forecast){
        forecast.let {

        }
    }

    fun showUpdateProgress(){
        progressBar.visibility = VISIBLE
    }

    fun hideUpdateProgress(){
        progressBar.visibility = GONE
    }
}