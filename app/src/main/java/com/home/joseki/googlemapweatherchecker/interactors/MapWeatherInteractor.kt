package com.home.joseki.googlemapweatherchecker.interactors

import android.annotation.SuppressLint
import com.home.joseki.googlemapweatherchecker.model.CityList
import com.home.joseki.googlemapweatherchecker.model.Coord
import com.home.joseki.googlemapweatherchecker.model.Forecast
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import com.home.joseki.googlemapweatherchecker.repositories.ICityRepository
import com.home.joseki.googlemapweatherchecker.repositories.IMapWeatherRepository
import io.reactivex.Observable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MapWeatherInteractor @Inject constructor(
    private val weatherRepository: IMapWeatherRepository,
    private val cityRepository: ICityRepository
): IMapWeatherInteractor {

    override fun getWeatherByAllCities(): Single<List<WeatherInfo>> =
        cityRepository.getCities()
            .flatMapObservable {
                getWeatherIterable(it)
            }
            .toList()

    override fun getForecast(coord: Coord): Observable<Forecast> = weatherRepository.getForecast(coord)

    override fun getCities(): Single<CityList> = cityRepository.getCities()

    override fun getWeatherByCoord(coord: Coord): Observable<WeatherInfo> = weatherRepository.getWeather(coord)


    private fun getWeatherIterable(cityList: CityList): Observable<WeatherInfo>{
        return Observable.fromIterable(cityList.Cities)
            .flatMap { weatherRepository.getWeather(Coord(it.lon, it.lat)) }
    }

    private fun getWeekForecast(forecast: Forecast): Observable<Forecast>{
        val date: Date?

        forecast.let {
            it.list
                ?.firstOrNull()
                ?.dtTxt
                ?.let { dt ->  date = SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN).parse(dt) }

            //складываем форкасты по дате начиная от полученной
        }

        Observable.fromIterable(forecast.list)
            .map { it }

        return null
    }
}