package com.home.joseki.googlemapweatherchecker.di.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.home.joseki.googlemapweatherchecker.web.api.CustomInterceptor
import com.home.joseki.googlemapweatherchecker.web.api.IWeatherApi
import com.home.joseki.googlemapweatherchecker.web.api.WeatherApiProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import toothpick.config.Module

class ApiModule: Module() {
    init {
        bind(OkHttpClient::class.java).toInstance(
            OkHttpClient.Builder()
                .addInterceptor(CustomInterceptor())
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build())
        bind(IWeatherApi::class.java).toProvider(WeatherApiProvider::class.java).providesSingletonInScope()
    }
}