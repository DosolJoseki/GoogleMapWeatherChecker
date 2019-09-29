package com.home.joseki.googlemapweatherchecker.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.reactivex.Maybe
import java.io.IOException
import java.util.*

class CityGPSSource(private val context: Context) : ICityGPSSource {

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION])
    override fun getLastLocation(): Location? {
        val mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val mGpsLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (!mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && !mGpsLocationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER)) {
            return null
        }

        //проверка, есть ли у программы разрешение пользователя на использование геолокации
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null
        }

        //драная локация... определяется очень криво, поэтому пытаемся определить ее разными способами
        var location: Location? = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location == null) {
            location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }
        if (location == null) {
            location = mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
        }

        val gpsLocation = mGpsLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        return if (gpsLocation != null && location != null && gpsLocation.time > location.time) {
            gpsLocation
        } else {
            location
        }
    }

    override fun getGpsCity(): Maybe<String> =
        Maybe.create<String> { emitter ->
            if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                emitter.onComplete()
                return@create
            }
            val location = getLastLocation()
            if (location == null) {
                emitter.onComplete()
                return@create
            }

            val gc = Geocoder(context, Locale("ru"))
            try {
                var result: String? = null
                for (i in 0..9) {
                    result = gc.getFromLocation(location.latitude, location.longitude, 1)[0].locality
                    if (result != null) {
                        break
                    }
                }

                emitter.onComplete()
            } catch (e: IOException) {
                emitter.onError(e)
            }
        }
}