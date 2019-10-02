package com.home.joseki.googlemapweatherchecker.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import com.home.joseki.googlemapweatherchecker.di.providers.SharedPreferencesProvider
import toothpick.config.Module

class AppModule(context: Context): Module() {
    init {
        bind(Context::class.java).toInstance(context)
        bind(AssetManager::class.java).toInstance(context.assets)
        bind(SharedPreferences::class.java).toProvider(SharedPreferencesProvider::class.java).singletonInScope()
    }
}