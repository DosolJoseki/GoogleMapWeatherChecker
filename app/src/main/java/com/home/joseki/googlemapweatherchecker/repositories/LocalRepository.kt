package com.home.joseki.googlemapweatherchecker.repositories

import android.content.SharedPreferences
import com.google.gson.Gson
import com.home.joseki.googlemapweatherchecker.model.CityInfo
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

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

    override fun setWeatherInfo(weatherInfo: WeatherInfo) {
        with(sharedPreferences.edit()) {
            putString(PREFS_WEATHER, Gson().toJson(weatherInfo))
            commit()
        }
    }

    override fun setForecast(forecast: Forecast) {
        with(sharedPreferences.edit()) {
            putString(PREFS_FORECAST, Gson().toJson(forecast))
            commit()
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

    override fun getWeatherInfo(): WeatherInfo {
        return sharedPreferences.let {
            Gson().fromJson(it.getString(PREFS_WEATHER, ""), WeatherInfo::class.java)
        }
    }

    override fun getForecast(): Forecast {
        return sharedPreferences.let {
            Gson().fromJson(it.getString(PREFS_FORECAST, ""), Forecast::class.java)
        }
    }
}