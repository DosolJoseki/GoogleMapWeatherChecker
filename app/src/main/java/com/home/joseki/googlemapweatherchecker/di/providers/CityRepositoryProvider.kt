package com.home.joseki.googlemapweatherchecker.di.providers

import com.home.joseki.googlemapweatherchecker.data.CityListSource
import com.home.joseki.googlemapweatherchecker.data.ICityGPSSource
import com.home.joseki.googlemapweatherchecker.repositories.CityRepository
import com.home.joseki.googlemapweatherchecker.repositories.ICityRepository
import javax.inject.Inject
import javax.inject.Provider

class CityRepositoryProvider @Inject constructor(
    private val cityGPSSource: ICityGPSSource,
    private val cityListSource: CityListSource
): Provider<ICityRepository> {
    override fun get(): ICityRepository = CityRepository(cityGPSSource, cityListSource)
}