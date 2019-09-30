package com.home.joseki.googlemapweatherchecker.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.home.joseki.googlemapweatherchecker.model.CityList
import io.reactivex.Single
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class CityListSource @Inject constructor(
    private val assetManager: AssetManager
) : ICityListSource {

    companion object {
        private const val FILE_NAME = "cities.txt"
    }

    override fun getCities(): Single<CityList> =
        Single.fromCallable {
            BufferedReader(InputStreamReader(assetManager.open(FILE_NAME))).use {
            Gson().fromJson(it, CityList::class.java)
            }
        }
}