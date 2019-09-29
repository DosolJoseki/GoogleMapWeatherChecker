package com.home.joseki.googlemapweatherchecker.di.providers

import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import com.home.joseki.googlemapweatherchecker.repositories.MapWeatherRepository
import com.home.joseki.googlemapweatherchecker.web.api.IWeatherApi
import javax.inject.Inject
import javax.inject.Provider

class MapWeatherRepositoryProvidere @Inject constructor(
    private val api: IWeatherApi
): Provider<IMapWeatherRepository> {
    override fun get(): IMapWeatherRepository = MapWeatherRepository(api)
}