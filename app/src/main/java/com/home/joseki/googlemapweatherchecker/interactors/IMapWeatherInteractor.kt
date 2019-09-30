package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.CityList
import com.home.joseki.googlemapweatherchecker.model.Coord
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single

interface IMapWeatherInteractor {
    fun getWeatherByCoord(coord: Coord): Observable<WeatherInfo>
    fun getCities(): Single<CityList>
    fun getForecast(coord: Coord): Observable<Forecast>
    fun getWeatherByAllCities(): Single<List<WeatherInfo>>
}