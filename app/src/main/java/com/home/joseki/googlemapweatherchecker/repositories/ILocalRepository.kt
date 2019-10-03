package com.home.joseki.googlemapweatherchecker.repositories

import com.home.joseki.googlemapweatherchecker.model.CityInfo
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.Maybe

interface ILocalRepository {
    fun getGpsCity(): Maybe<CityInfo>
    fun getWeatherInfo(): WeatherInfo
    fun getForecast():Forecast

    fun setGpsCity(cityInfo: CityInfo)
    fun setWeatherInfo(weatherInfo: WeatherInfo)
    fun setForecast(forecast: Forecast)
}