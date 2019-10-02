package com.home.joseki.googlemapweatherchecker.fragments

import com.home.joseki.googlemapweatherchecker.interactors.IForecastInteractor
import com.home.joseki.googlemapweatherchecker.interactors.IMapWeatherInteractor
import com.home.joseki.googlemapweatherchecker.model.ForecastItem
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class WeatherForecastFragmentPresenter(
    private val view: WeatherForecastFragment,
    private val interactor: IForecastInteractor,
    weatherInfo: WeatherInfo
) {

    private var compositeDisposable = CompositeDisposable()

    init {
        view.showUpdateProgress()

        compositeDisposable.add(
        interactor.getForecast(weatherInfo.coord)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.hideUpdateProgress()
                    view.fillWeekForecast(interactor.getWeekForecast(it))
                },
                {
                    Timber.e(it)
                })
        )
    }

    fun onItemSelected(forecastItem: ForecastItem){
        view.fillDailyForecast(interactor.getDailyForecast(forecastItem))
    }
}