package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.*
import com.home.joseki.googlemapweatherchecker.repositories.ILocalRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject

class ForecastInteractor @Inject constructor(
    private val weatherRepository: IMapWeatherRepository,
    private val localRepository: ILocalRepository
): IForecastInteractor {

    private lateinit var selectedForecast: Forecast

    companion object {
        private const val START_INDEX = 0
        private const val START_INDEX_TIME = 11
        private const val END_INDEX_DATE = 10
    }

    override fun getForecast(coord: Coord): Observable<Forecast> =
        weatherRepository.getForecast(coord)
            .map {
                localRepository.setForecast(it, coord)
                it
            }

    override fun getWeekForecast(forecast: Forecast): List<ForecastItem> {
        selectedForecast = forecast
        val date: String
        var fc: List<ForecastItem> = ArrayList()

        forecast.let {
            it.list
                .firstOrNull()
                ?.dtTxt
                ?.let { dt ->
                    date = dt.substring(START_INDEX_TIME)
                    fc = it.list
                        .filter { a ->
                            a.dtTxt.substring(START_INDEX_TIME) == date
                        }

                    return fc
                }
        }

        return fc
    }

    override fun getDailyForecast(item: ForecastItem): List<ForecastItem> {
        var fc: List<ForecastItem>

        selectedForecast.list.let {
            fc = it.filter { a ->
                a.dtTxt.substring(START_INDEX, END_INDEX_DATE) == item.dtTxt.substring(START_INDEX, END_INDEX_DATE)
            }

            return fc
        }
    }

    override fun getLocalWeather(coord: Coord): Maybe<Forecast> =
        localRepository.getForecast(coord)
}