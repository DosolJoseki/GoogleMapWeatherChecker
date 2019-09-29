package com.home.joseki.googlemapweatherchecker.data

import com.home.joseki.googlemapweatherchecker.model.CityList
import io.reactivex.Single

interface ICityListSource {
    fun getCities(): Single<CityList>
}