package com.home.joseki.googlemapweatherchecker.interactors

import com.home.joseki.googlemapweatherchecker.model.*
import com.home.joseki.googlemapweatherchecker.repositories.ICityRepository
import com.home.joseki.googlemapweatherchecker.repositories.ILocalRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ForecastInteractor @Inject constructor(
    private val weatherRepository: IMapWeatherRepository,
    private val cityRepository: ICityRepository,
    private val localRepository: ILocalRepository
): IForecastInteractor {
    private lateinit var selectedForecast: Forecast

    companion object {
        private const val START_INDEX = 0
        private const val START_INDEX_TIME = 11
        private const val END_INDEX_DATE = 10
    }

    override fun getWeatherByAllCities(): Single<List<WeatherInfo>> =
        cityRepository.getCities()
            .flatMapObservable {
                getWeatherIterable(it)
            }
            .toList()

    override fun getForecast(coord: Coord): Observable<Forecast> =
        weatherRepository.getForecast(coord)

    override fun getCities(): Single<CityList> = cityRepository.getCities()

    override fun getWeatherByCoord(coord: Coord): Observable<WeatherInfo> =
        weatherRepository.getWeather(coord)


    private fun getWeatherIterable(cityList: CityList): Observable<WeatherInfo> {
        return Observable.fromIterable(cityList.Cities)
            .flatMap {
                weatherRepository.getWeather(Coord(it.lon, it.lat))
            }
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
}