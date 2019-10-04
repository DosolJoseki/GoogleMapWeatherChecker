package com.home.joseki.googlemapweatherchecker.repositories

import android.content.SharedPreferences
import com.google.gson.Gson
import com.home.joseki.googlemapweatherchecker.model.CityInfo
import com.home.joseki.googlemapweatherchecker.model.Coord
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import com.google.gson.reflect.TypeToken

class LocalRepository(
    private val sharedPreferences: SharedPreferences
) : ILocalRepository {

    companion object {
        private const val PREFS_CITY = "sharedPreferencesCity"
        private const val PREFS_WEATHER = "sharedPreferencesWeather"
        private const val PREFS_FORECAST = "sharedPreferencesForecast"
    }

    override fun setGpsCity(cityInfo: CityInfo) {
        with(sharedPreferences.edit()) {
            putString(PREFS_CITY, Gson().toJson(cityInfo))
            commit()
        }
    }

    override fun setWeatherInfo(weatherInfo: MutableList<WeatherInfo>) {
        with(sharedPreferences.edit()) {
            val weatherType = object : TypeToken<MutableList<WeatherInfo>>() {
            }.type
            putString(PREFS_WEATHER, Gson().toJson(weatherInfo, weatherType))
            commit()
        }
    }

    override fun setForecast(forecast: Forecast, coord: Coord) {
        val forecastType = object : TypeToken<Map<String, Forecast>>() {
        }.type
        var map = mutableMapOf<String, Forecast>()

        sharedPreferences.let {
            try {
                map = Gson().fromJson(it.getString(PREFS_FORECAST, null), forecastType)

                with(sharedPreferences.edit()) {
                    map[Gson().toJson(coord)] = forecast
                    putString(PREFS_FORECAST, Gson().toJson(map, forecastType))
                    commit()
                }
            } catch (e: Exception){
                with(sharedPreferences.edit()) {
                    map[Gson().toJson(coord)] = forecast
                    putString(PREFS_FORECAST, Gson().toJson(map, forecastType))
                    commit()
                }
            }
        }
    }

    override fun getGpsCity(): Maybe<CityInfo> =
        Maybe.create<CityInfo> { emitter ->
            sharedPreferences.getString(PREFS_CITY, null)?.let {
                emitter.onSuccess(Gson().fromJson(it, CityInfo::class.java))
            }
                ?: emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())

    override fun getWeatherInfo(): Maybe<MutableList<WeatherInfo>> =
        Maybe.create<MutableList<WeatherInfo>> {emitter ->
            sharedPreferences.getString(PREFS_WEATHER, null)?.let {
                val weatherType = object : TypeToken<MutableList<WeatherInfo>>() {
                }.type
                emitter.onSuccess(Gson().fromJson(it, weatherType))
            }
                ?: emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())


    override fun getForecast(coord: Coord): Maybe<Forecast> =
        Maybe.create<Forecast>{emitter ->
            sharedPreferences.getString(PREFS_FORECAST, null)?.let {
                val forecastType = object : TypeToken<MutableMap<String, Forecast>>() {
                }.type
                val map: MutableMap<String, Forecast> = Gson().fromJson(it, forecastType)
                emitter.onSuccess(map[Gson().toJson(coord)]!!)
            }
                ?: emitter.onComplete()
        }
            .subscribeOn(Schedulers.io())
}