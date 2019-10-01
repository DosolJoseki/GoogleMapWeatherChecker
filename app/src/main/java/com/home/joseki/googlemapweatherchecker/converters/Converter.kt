package com.home.joseki.googlemapweatherchecker.converters

object Converter {
    ////(32 °F − 32) × 5/9 = 0 °C
    fun fahrenheitToCelsius(double: Double): String{
         return String.format("%.1f", ((double - 32) * 0.5555))
    }
}