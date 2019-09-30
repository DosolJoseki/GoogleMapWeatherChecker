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
        //if(view.pokemonAdapter.itemCount == 0)
        //    getPokemons(isRandom = false, addToList = false)

        compositeDisposable.add(
            interactor.getWeatherByAllCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.setWeatherPins(it)
                        view.showUpdateProgress(false)
                    },
                    {
                        Timber.e(it)
                    }
                )
        )

       /* compositeDisposable.add(
        interactor.getWeatherByCoord("12", "42")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.setWeather(it)
                    view.showUpdateProgress(false)
                },
                {
                    Timber.e(it)
                }
            )
        )*/

        /*compositeDisposable.add(
            interactor.getForecast("12", "42")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        //view.setWeather(it)
                        view.showUpdateProgress(false)
                    },
                    {
                        Timber.e(it)
                    }
                )
        )*/

       /* compositeDisposable.add(
            interactor.getCities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.setCity(it)
                        view.showUpdateProgress(false)
                    },
                    {
                        Timber.e(it)
                    }
                )
        )*/
    }

    fun onMarkerClick(weatherInfo: WeatherInfo): Boolean{
        weatherInfo.let {
            router.navigateTo(Screens.WeatherInfoScreen(weatherInfo))
        }

        return false
    }
}