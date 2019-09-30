package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.CityList
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single

interface IMapWeatherInteractor {
    fun getWeatherByCoord(lon: String, lat: String): Observable<WeatherInfo>
    fun getCities(): Single<CityList>
    fun getForecast(lon: String, lat: String): Observable<Forecast>
    fun getWeatherByAllCities(): Single<List<WeatherInfo>>
}