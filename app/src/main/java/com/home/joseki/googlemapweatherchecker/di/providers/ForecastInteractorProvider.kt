package com.home.joseki.googlemapweatherchecker.di.providers

import com.home.joseki.googlemapweatherchecker.interactors.ForecastInteractor
import com.home.joseki.googlemapweatherchecker.interactors.IForecastInteractor
import com.home.joseki.googlemapweatherchecker.repositories.ILocalRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import javax.inject.Inject
import javax.inject.Provider

class ForecastInteractorProvider @Inject constructor(
    private val weatherRepository: IMapWeatherRepository,
    private val localRepository: ILocalRepository
):Provider<IForecastInteractor> {
    override fun get(): IForecastInteractor = ForecastInteractor(weatherRepository, localRepository)
}