package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.*
import com.home.joseki.googlemapweatherchecker.repositories.ICityRepository
import com.home.joseki.googlemapweatherchecker.repositories.ILocalRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MapWeatherInteractor @Inject constructor(
    private val weatherRepository: IMapWeatherRepository,
    private val cityRepository: ICityRepository,
    private val localRepository: ILocalRepository
) : IMapWeatherInteractor {

    override fun getGpsCity(): Maybe<CityInfo> =
        localRepository.getGpsCity()
            .switchIfEmpty {
                cityRepository.getGpsLocation()
            }
            .map {
                localRepository.setGpsCity(it)
                it
            }


    override fun getWeatherByAllCities(): Single<List<WeatherInfo>> =
        getCities()
            .flatMapObservable {
                getWeatherIterable(it)
            }
            .toList()

    override fun getCities(): Single<CityList> {
        var cityList: ArrayList<CityInfo> = ArrayList()

        return cityRepository.getCities()
            .map {
                cityList = it.Cities as ArrayList<CityInfo>
            }
            .toMaybe()
            .flatMap {
                getGpsCity()
            }
            .map {
                cityList.add(it)
            }
            .toSingle()
            .map {
                CityList(cityList)
            }
    }

    override fun getWeatherByCoord(coord: Coord): Observable<WeatherInfo> =
        weatherRepository.getWeather(coord)

    private fun getWeatherIterable(cityList: CityList): Observable<WeatherInfo> {
        return Observable.fromIterable(cityList.Cities)
            .flatMap {
                weatherRepository.getWeather(Coord(it.lon, it.lat))
            }
    }
}