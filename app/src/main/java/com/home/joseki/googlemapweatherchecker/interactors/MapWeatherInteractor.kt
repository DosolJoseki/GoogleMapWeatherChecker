package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.CityList
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import com.home.joseki.googlemapweatherchecker.repositories.ICityRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MapWeatherInteractor @Inject constructor(
    private val weatherRepository: IMapWeatherRepository,
    private val cityRepository: ICityRepository
): IMapWeatherInteractor {
    override fun getCities(): Single<CityList> = cityRepository.getCities()

    override fun getForecast(lon: String, lat: String): Observable<WeatherInfo> = weatherRepository.getForecast(lon, lat)
}