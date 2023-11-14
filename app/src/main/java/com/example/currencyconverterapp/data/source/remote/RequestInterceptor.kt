package com.example.currencyconverterapp.data.source.remote

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url
            .newBuilder()
            .build()
        val request = originalRequest.newBuilder()
            .url(newUrl)
            .addHeader("apikey", "FIPZVI7fL8pov6xPV3czgwE7Z482q1Kp")
            .build()
        return chain.proceed(request)
    }
}