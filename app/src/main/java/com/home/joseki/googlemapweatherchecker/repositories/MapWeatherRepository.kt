package com.home.joseki.googlemapweatherchecker.repositories

import com.home.joseki.googlemapweatherchecker.model.Coord
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.ForecastItem
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

    override fun getWeather(coord: Coord): Observable<WeatherInfo> =
        weatherApi.getWeatherByCoord(coord.lon, coord.lat, UNITS, HOST, API_KEY)
            .subscribeOn(Schedulers.io())

    override fun getForecast(coord: Coord): Observable<Forecast> =
        weatherApi.getForecastByCoord(coord.lon, coord.lat, HOST, API_KEY)
            .subscribeOn(Schedulers.io())
}