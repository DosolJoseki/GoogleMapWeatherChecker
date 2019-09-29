package com.home.joseki.googlemapweatherchecker.di.providers

import com.home.joseki.googlemapweatherchecker.interactors.IMapWeatherInteractor
import com.home.joseki.googlemapweatherchecker.interactors.MapWeatherInteractor
import com.home.joseki.googlemapweatherchecker.repositories.ICityRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import javax.inject.Inject
import javax.inject.Provider

class MapWeatherInteractorProvider @Inject constructor(
    private val weatherRepository: IMapWeatherRepository,
    private val cityRepository: ICityRepository
): Provider<IMapWeatherInteractor>{
    override fun get(): IMapWeatherInteractor = MapWeatherInteractor(weatherRepository, cityRepository)
}