package com.home.joseki.googlemapweatherchecker.repositories

import com.home.joseki.googlemapweatherchecker.model.CityInfo
import com.home.joseki.googlemapweatherchecker.model.CityList
import io.reactivex.Maybe

import io.reactivex.Single

interface ICityRepository {
    fun getCities(): Single<CityList>
    fun getGpsLocation(): Maybe<CityInfo>
}