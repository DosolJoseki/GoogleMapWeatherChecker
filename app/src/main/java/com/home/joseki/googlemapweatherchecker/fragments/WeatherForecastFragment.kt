package com.home.joseki.googlemapweatherchecker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.home.joseki.googlemapweatherchecker.R
import com.home.joseki.googlemapweatherchecker.adapters.WeeklyWeatherAdapter
import com.home.joseki.googlemapweatherchecker.di.Scopes
import com.home.joseki.googlemapweatherchecker.interactors.IForecastInteractor
import com.home.joseki.googlemapweatherchecker.model.ForecastItem
import com.home.joseki.googlemapweatherchecker.model.WeatherInfo
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_weather_forecast.*
import toothpick.Toothpick
import java.lang.StringBuilder

class WeatherForecastFragment(
    private val weatherInfo: WeatherInfo
): Fragment() {

    private lateinit var presenter: WeatherForecastFragmentPresenter
    private val weeklyWeatherAdapter: WeeklyWeatherAdapter = WeeklyWeatherAdapter()
    private val dailyWeatherAdapter: WeeklyWeatherAdapter = WeeklyWeatherAdapter()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        @JvmStatic
        fun newInstance(weatherInfo: WeatherInfo) =
            WeatherForecastFragment(weatherInfo)

        private const val START_INDEX = 0
        private const val END_INDEX_DATE = 10
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scope = Toothpick.openScope(Scopes.APP)
        Toothpick.inject(this, scope)
        presenter = WeatherForecastFragmentPresenter(
            this,
            scope.getInstance(IForecastInteractor::class.java),
            weatherInfo
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = weeklyWeatherAdapter

        recyclerViewDetailedWeather.layoutManager = LinearLayoutManager(context)
        recyclerViewDetailedWeather.adapter = dailyWeatherAdapter

        compositeDisposable.add(
            weeklyWeatherAdapter.itemClickListener.doOnNext {
                presenter.onItemSelected(it)
                tvDay.text = StringBuilder().append(weatherInfo.name).append(", ").append(it.dtTxt.substring(START_INDEX, END_INDEX_DATE))
            }.subscribe()
        )

        tvDay.text = weatherInfo.name
    }

    fun fillWeekForecast(list: List<ForecastItem>){
        weeklyWeatherAdapter.setItems(list)
    }

    fun fillDailyForecast(list: List<ForecastItem>){
        dailyWeatherAdapter.setItems(list)
        BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun showUpdateProgress(){
        progressBar.visibility = VISIBLE
    }

    fun hideUpdateProgress(){
        progressBar.visibility = GONE
    }
}