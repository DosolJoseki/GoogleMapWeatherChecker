package com.home.joseki.googlemapweatherchecker

import com.home.joseki.googlemapweatherchecker.di.navigation.MainRouter
import com.home.joseki.googlemapweatherchecker.di.navigation.Screens
import io.reactivex.disposables.CompositeDisposable

class MainActivityPresenter(
    private val router: MainRouter
) {
    private val compositeDisposable = CompositeDisposable()

    init {
        router.navigateTo(Screens.MapScreen)
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }

    fun onBackPressed(){
        router.backTo(Screens.MapScreen)
    }
}