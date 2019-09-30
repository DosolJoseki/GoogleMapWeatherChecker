package com.home.joseki.googlemapweatherchecker.fragments

import com.home.joseki.googlemapweatherchecker.interactors.IMapWeatherInteractor
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import timber.log.Timber

class WeatherForecastFragmentPresenter(
    private val view: WeatherForecastFragment,
    private val router: Router,
    private val interactor: IMapWeatherInteractor,
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
                    view.fillForecast(it)
                },
                {
                    Timber.e(it)
                })
        )
    }
}