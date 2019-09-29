package com.home.joseki.googlemapweatherchecker.model

import com.google.gson.annotations.SerializedName

data class CityInfo(
    @SerializedName("city")
    val city: String ="",
    @SerializedName("city_ascii")
    val cityAscii: String = "",
    @SerializedName("lat")
    val lat: String = "",
    @SerializedName("lng")
    val lon:String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("iso2")
    val iso2: String = "",
    @SerializedName("iso3")
    val iso3: String = "",
    @SerializedName("admin_name")
    val adminName: String = "",
    @SerializedName("capital")
    val capitalCityName: String,
    @SerializedName("id")
    val id: String = ""
)