package com.home.joseki.googlemapweatherchecker.repositories

import com.home.joseki.googlemapweatherchecker.model.CityInfo
import com.home.joseki.googlemapweatherchecker.model.Coord
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.Maybe

interface ILocalRepository {
    fun getGpsCity(): Maybe<CityInfo>
    fun getWeatherInfo(): Maybe<MutableList<WeatherInfo>>
    fun getForecast(coord: Coord): Maybe<Forecast>

    fun setGpsCity(cityInfo: CityInfo)
    fun setWeatherInfo(weatherInfo: MutableList<WeatherInfo>)
    fun setForecast(forecast: Forecast, coord: Coord)
}