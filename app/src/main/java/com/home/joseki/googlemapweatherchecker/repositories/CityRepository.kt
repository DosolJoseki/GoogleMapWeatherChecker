package com.home.joseki.googlemapweatherchecker.repositories

import com.home.joseki.googlemapweatherchecker.data.ICityGPSSource
import com.home.joseki.googlemapweatherchecker.data.ICityListSource
import com.home.joseki.googlemapweatherchecker.model.CityInfo
import com.home.joseki.googlemapweatherchecker.model.CityList
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CityRepository(
    private val cityGPSSource: ICityGPSSource,
    private val cityListSource: ICityListSource
): ICityRepository {
    override fun getCities(): Single<CityList> =
        cityListSource.getCities()
            .subscribeOn(Schedulers.io())

    override fun getGpsLocation(): Maybe<CityInfo> =
        cityGPSSource.getGpsCity()
            .subscribeOn(Schedulers.io())
            .map {
                CityInfo(capitalCityName = "gpsLocation", lat = it.latitude, lon = it.longitude)
            }
}