package com.home.joseki.googlemapweatherchecker.repositories

import com.home.joseki.googlemapweatherchecker.model.Coord
import com.home.joseki.googlemapweatherchecker.model.Forecast
import io.reactivex.Observable
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo

interface IMapWeatherRepository {
    fun getWeather(coord: Coord): Observable<WeatherInfo>

    fun getForecast(coord: Coord): Observable<Forecast>
}