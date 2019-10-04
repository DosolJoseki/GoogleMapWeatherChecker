package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.*
import io.reactivex.Maybe
import io.reactivex.Observable

interface IForecastInteractor {
    fun getForecast(coord: Coord): Observable<Forecast>
    fun getWeekForecast(forecast: Forecast): List<ForecastItem>
    fun getDailyForecast(item: ForecastItem): List<ForecastItem>
    fun getLocalWeather(coord: Coord): Maybe<Forecast>
}