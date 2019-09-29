package com.home.joseki.googlemapweatherchecker.repositories

import io.reactivex.Observable
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo

interface IMapWeatherRepository {
    fun getForecast(lon: String, lat: String): Observable<WeatherInfo>
}