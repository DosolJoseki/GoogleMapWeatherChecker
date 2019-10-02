package com.home.joseki.googlemapweatherchecker.di.providers

import android.content.SharedPreferences
import com.home.joseki.googlemapweatherchecker.repositories.ILocalRepository
import com.home.joseki.googlemapweatherchecker.repositories.LocalRepository
import javax.inject.Inject
import javax.inject.Provider

class LocalRepositoryProvider @Inject constructor(
    private val sharedPreferences: SharedPreferences
): Provider<ILocalRepository> {
    override fun get(): ILocalRepository = LocalRepository(sharedPreferences)
}