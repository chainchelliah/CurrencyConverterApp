package com.example.currencyconverterapp.data.source.remote

import com.example.currencyconverterapp.data.source.remote.dto.IBanValidatorDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IBanValidatorApiService {

    @GET("bank_data/iban_validate")
    suspend fun getAccountDetails(@Query("iban_number") accountNumber : String): IBanValidatorDto

}