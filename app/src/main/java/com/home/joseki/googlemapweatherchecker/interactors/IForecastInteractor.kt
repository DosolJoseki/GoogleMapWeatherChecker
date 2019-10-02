package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.*
import io.reactivex.Observable
import io.reactivex.Single

interface IForecastInteractor {
    fun getWeatherByCoord(coord: Coord): Observable<WeatherInfo>
    fun getCities(): Single<CityList>
    fun getForecast(coord: Coord): Observable<Forecast>
    fun getWeatherByAllCities(): Single<List<WeatherInfo>>
    fun getWeekForecast(forecast: Forecast): List<ForecastItem>
    fun getDailyForecast(item: ForecastItem): List<ForecastItem>
}