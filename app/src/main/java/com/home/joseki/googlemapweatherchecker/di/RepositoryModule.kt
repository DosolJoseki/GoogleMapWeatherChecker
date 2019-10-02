package com.home.joseki.googlemapweatherchecker.di

import com.home.joseki.googlemapweatherchecker.di.providers.LocalRepositoryProvider
import com.home.joseki.googlemapweatherchecker.di.providers.MapWeatherRepositoryProvidere
import com.home.joseki.googlemapweatherchecker.repositories.ILocalRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import toothpick.config.Module

class RepositoryModule: Module() {
    init {
        bind(IMapWeatherRepository::class.java).toProvider(MapWeatherRepositoryProvidere::class.java).providesSingletonInScope()
        bind(ILocalRepository::class.java).toProvider(LocalRepositoryProvider::class.java).providesSingletonInScope()
    }
}