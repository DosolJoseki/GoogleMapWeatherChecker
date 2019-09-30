package com.home.joseki.googlemapweatherchecker.repositories

import com.home.joseki.googlemapweatherchecker.model.Forecast
import io.reactivex.Observable
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import com.home.joseki.googlemapweatherchecker.web.api.IWeatherApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapWeatherRepository @Inject constructor(
    private val weatherApi: IWeatherApi
): IMapWeatherRepository {

    companion object {
        private const val UNITS = "metric"
        private const val HOST = "community-open-weather-map.p.rapidapi.com"
        private const val API_KEY = "45e3b571b8msh1525196d1d6cb36p19f5f2jsnda1b48f5181f"
    }

    override fun getWeather(lon: String, lat: String): Observable<WeatherInfo> =
        weatherApi.getWeatherByCoord(lon, lat, UNITS, HOST, API_KEY)
            .subscribeOn(Schedulers.io())

    override fun getForecast(lon: String, lat: String): Observable<Forecast> =
        weatherApi.getForecastByCoord(lon, lat, UNITS, HOST, API_KEY)
            .subscribeOn(Schedulers.io())
}