package com.home.joseki.googlemapweatherchecker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.joseki.googlemapweatherchecker.R
import com.home.joseki.googlemapweatherchecker.converters.Converter
import com.home.joseki.googlemapweatherchecker.model.ForecastItem
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.list_item_weekly.view.*
import kotlin.collections.ArrayList


class WeeklyWeatherAdapter : RecyclerView.Adapter<WeeklyWeatherAdapter.WeeklyWeatherViewHolder>() {
    private var items: ArrayList<ForecastItem> = ArrayList()

    val itemClickListener: PublishSubject<ForecastItem> = PublishSubject.create()

    fun setItems(i: List<ForecastItem>) {
        items.clear()
        items.addAll(i)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyWeatherViewHolder =
        WeeklyWeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_weekly,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WeeklyWeatherViewHolder, position: Int) =
        holder.bind(items[position])

    inner class WeeklyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ForecastItem) {
            itemView.setOnClickListener { itemClickListener.onNext(item) }

            itemView.tvTemp.text = item.main.let {
                Converter.fahrenheitToCelsius(it!!.temp)
            }
            itemView.tvClouds.text = item.weather.let {
                it!!.first().description
            }
            itemView.tvWind.text = item.wind.let {
                it!!.speed + "m/s, " + it.deg + "°"
            }
            itemView.tvPressure.text = item.main.let {
                it!!.pressure
            }
            itemView.tvDaylight.text = "-"
            itemView.tvDateTime.text = item.dtTxt

        }
    }

    override fun getItemCount(): Int = items.size
}