package com.example.currencyconverterapp.di

import com.example.currencyconverterapp.data.Constants.BASE_URL
import com.example.currencyconverterapp.data.source.remote.CurrencyConverterApiService
import com.example.currencyconverterapp.data.source.remote.IBanValidatorApiService
import com.example.currencyconverterapp.data.source.remote.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val certPinner = CertificatePinner.Builder()
        .add("api.apilayer.com", "sha256/jKx/K3o0EaswUsjM80WKm89ukEkIH8ZVlVJ6daRaIyg=")
        .build()

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }
    @Singleton
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient.Builder().certificatePinner(certPinner).addInterceptor(RequestInterceptor()).addInterceptor(interceptor)
            .build()


    @Singleton
    @Provides
    fun provideRetrofitIBanValidatorService(okHttpClient: OkHttpClient): IBanValidatorApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(okHttpClient)
            .build()
            .create(IBanValidatorApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofitCurrencyConverterService(okHttpClient: OkHttpClient): CurrencyConverterApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(okHttpClient)
            .build()
            .create(CurrencyConverterApiService::class.java)
}