package com.home.joseki.googlemapweatherchecker.web.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class WeatherApiProvider @Inject constructor(
    private val okHttpClient: OkHttpClient
): Provider<IWeatherApi> {

    companion object {
        private const val BASE_URL: String = "https://community-open-weather-map.p.rapidapi.com/"
    }

    override fun get(): IWeatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(IWeatherApi::class.java)
}