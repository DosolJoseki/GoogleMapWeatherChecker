package com.home.joseki.googlemapweatherchecker.web.api

import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface IWeatherApi {
    @GET("weather")
    fun getWeatherByCoord(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("units") units: String,
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") apiKey: String
    ): Observable<WeatherInfo>

    @GET("forecast")
    fun getForecastByCoord(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Header("x-rapidapi-host") host: String,
        @Header("x-rapidapi-key") apiKey: String
    ): Observable<Forecast>
}