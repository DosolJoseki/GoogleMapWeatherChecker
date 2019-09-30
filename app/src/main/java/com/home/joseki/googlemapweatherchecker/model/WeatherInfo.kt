package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    @SerializedName("dt")
    val dt: String,
    @SerializedName("rain")
    val rain: Rain,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("name")
    val name: String,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("id")
    val id: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("base")
    val base: String,
    @SerializedName("wind")
    val wind: Wind
)