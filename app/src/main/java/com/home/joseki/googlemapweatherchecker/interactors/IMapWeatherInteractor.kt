package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.*
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface IMapWeatherInteractor {
    fun getGpsCity(): Maybe<CityInfo>
    fun getWeatherByCoord(coord: Coord): Observable<WeatherInfo>
    fun getCities(): Single<CityList>
    fun getWeatherByAllCities(): Single<List<WeatherInfo>>
}