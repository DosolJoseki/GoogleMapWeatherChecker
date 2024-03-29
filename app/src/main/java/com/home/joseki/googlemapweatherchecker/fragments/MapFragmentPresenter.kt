package com.home.joseki.googlemapweatherchecker.fragments

import com.home.joseki.googlemapweatherchecker.di.navigation.Screens
import com.home.joseki.googlemapweatherchecker.interactors.IMapWeatherInteractor
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import timber.log.Timber

class MapFragmentPresenter(
    private val view: MapFragment,
    private val router: Router,
    private val interactor: IMapWeatherInteractor
) {
    private var compositeDisposable = CompositeDisposable()

    init {
        if (!view.isInitialazed)
            compositeDisposable.add(
                interactor.getWeatherByAllCities()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            view.setWeatherPins(it)
                            getLocation()
                        },
                        {
                            getLocalData()
                        }
                    )
            )
    }

    private fun getLocation(){
        compositeDisposable.add(
            interactor.getGpsCity()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.setCamPosition(it)
                    },
                    {
                        Timber.e(it)
                    }
                )
        )
    }

    private fun getLocalData() {
        compositeDisposable.add(
            interactor.getLocalWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.setWeatherPins(it)
                    },
                    {
                        Timber.e(it)
                    }
                )
        )
    }

    fun onMarkerClick(weatherInfo: WeatherInfo): Boolean {
        weatherInfo.let {
            router.navigateTo(Screens.WeatherInfoScreen(weatherInfo))
        }

        return false
    }
}