package com.home.joseki.googlemapweatherchecker.di

import com.home.joseki.googlemapweatherchecker.di.providers.CityRepositoryProvider
import com.home.joseki.googlemapweatherchecker.di.providers.ForecastInteractorProvider
import com.home.joseki.googlemapweatherchecker.di.providers.MapWeatherInteractorProvider
import com.home.joseki.googlemapweatherchecker.interactors.IForecastInteractor
import com.home.joseki.googlemapweatherchecker.interactors.IMapWeatherInteractor
import com.home.joseki.googlemapweatherchecker.repositories.ICityRepository
import toothpick.config.Module

class ProviderModule: Module() {
    init {
        bind(IMapWeatherInteractor::class.java).toProvider(MapWeatherInteractorProvider::class.java).providesSingletonInScope()
        bind(ICityRepository::class.java).toProvider(CityRepositoryProvider::class.java).providesSingletonInScope()
        bind(IForecastInteractor::class.java).toProvider(ForecastInteractorProvider::class.java).providesSingletonInScope()
    }
}