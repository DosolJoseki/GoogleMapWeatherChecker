package com.home.joseki.googlemapweatherchecker.di

import com.home.joseki.googlemapweatherchecker.data.ICityGPSSource
import com.home.joseki.googlemapweatherchecker.data.ICityListSource
import com.home.joseki.googlemapweatherchecker.di.providers.CityGPSSourceProvider
import com.home.joseki.googlemapweatherchecker.di.providers.CityListSourceProvider
import toothpick.config.Module

class SourceModule: Module() {
    init {
        bind(ICityGPSSource::class.java).toProvider(CityGPSSourceProvider::class.java).providesSingletonInScope()
        bind(ICityListSource::class.java).toProvider(CityListSourceProvider::class.java).providesSingletonInScope()
    }
}