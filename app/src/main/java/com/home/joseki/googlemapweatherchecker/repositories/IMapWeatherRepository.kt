package com.home.joseki.googlemapweatherchecker.repositories

import com.home.joseki.googlemapweatherchecker.model.Forecast
import io.reactivex.Observable
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo

interface IMapWeatherRepository {
    fun getWeather(lon: String, lat: String): Observable<WeatherInfo>

    fun getForecast(lon: String, lat: String): Observable<Forecast>
}