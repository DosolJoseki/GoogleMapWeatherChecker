package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.*
import com.home.joseki.googlemapweatherchecker.repositories.ICityRepository
import com.home.joseki.googlemapweatherchecker.repositories.ILocalRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MapWeatherInteractor @Inject constructor(
    private val weatherRepository: IMapWeatherRepository,
    private val cityRepository: ICityRepository,
    private val localRepository: ILocalRepository
) : IMapWeatherInteractor {
    override fun getGpsCity(): Maybe<CityInfo> =
        cityRepository.getGpsLocation()

    override fun getWeatherByAllCities(): Single<List<WeatherInfo>> =
        cityRepository.getCities()
            .flatMapObservable {
                getWeatherIterable(it)
            }
            .toList()

    override fun getCities(): Single<CityList> = cityRepository.getCities()

    override fun getWeatherByCoord(coord: Coord): Observable<WeatherInfo> =
        weatherRepository.getWeather(coord)


    private fun getWeatherIterable(cityList: CityList): Observable<WeatherInfo> {
        return Observable.fromIterable(cityList.Cities)
            .flatMap {
                weatherRepository.getWeather(Coord(it.lon, it.lat))
            }
    }
}