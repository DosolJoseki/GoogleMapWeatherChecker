package com.home.joseki.googlemapweatherchecker.web.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class CustomInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        //check body response
        response.body()?.source()
        return response
    }
}