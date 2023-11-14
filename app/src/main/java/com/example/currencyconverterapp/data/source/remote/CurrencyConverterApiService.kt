package com.example.currencyconverterapp.data.source.remote

import com.example.currencyconverterapp.data.source.remote.dto.CurrencyConverterDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConverterApiService {
    @GET("fixer/latest")
    suspend fun getCurrencies(@Query("base") base: String): CurrencyConverterDto
}