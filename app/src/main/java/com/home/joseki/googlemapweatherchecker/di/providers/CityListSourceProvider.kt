package com.home.joseki.googlemapweatherchecker.di.providers

import android.content.res.AssetManager
import com.home.joseki.googlemapweatherchecker.data.CityListSource
import com.home.joseki.googlemapweatherchecker.data.ICityListSource
import javax.inject.Inject
import javax.inject.Provider

class CityListSourceProvider @Inject constructor(
    private val assetManager: AssetManager
): Provider<ICityListSource> {
    override fun get(): ICityListSource = CityListSource(assetManager)
}