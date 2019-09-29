package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    @SerializedName("dt")
    private val dt: String,
    @SerializedName("rain")
    private val rain: Rain,
    @SerializedName("coord")
    private val coord: Coord,
    @SerializedName("weather")
    private val weather: List<Weather>,
    @SerializedName("name")
    private val name: String,
    @SerializedName("cod")
    private val cod: String,
    @SerializedName("main")
    private val main: Main,
    @SerializedName("clouds")
    private val clouds: Clouds,
    @SerializedName("id")
    private val id: String,
    @SerializedName("sys")
    private val sys: Sys,
    @SerializedName("base")
    private val base: String,
    @SerializedName("wind")
    private val wind: Wind
)