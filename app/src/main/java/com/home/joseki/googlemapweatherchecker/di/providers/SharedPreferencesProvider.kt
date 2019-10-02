package com.home.joseki.googlemapweatherchecker.di.providers

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Provider

class SharedPreferencesProvider @Inject constructor(
    private val context: Context
): Provider<SharedPreferences> {

    companion object {
        private const val PREFS_NAME = "myPrefs"
    }

    override fun get(): SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
}