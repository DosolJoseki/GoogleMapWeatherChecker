package com.home.joseki.googlemapweatherchecker.di.providers

import android.content.Context
import com.home.joseki.googlemapweatherchecker.data.CityGPSSource
import com.home.joseki.googlemapweatherchecker.data.ICityGPSSource
import javax.inject.Inject
import javax.inject.Provider

class CityGPSSourceProvider @Inject constructor(
    private val context: Context
) : Provider<ICityGPSSource> {
    override fun get(): ICityGPSSource = CityGPSSource(context)
}