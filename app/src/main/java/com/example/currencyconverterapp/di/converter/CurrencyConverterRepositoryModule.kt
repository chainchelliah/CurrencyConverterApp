package com.example.currencyconverterapp.di.converter

import com.example.currencyconverterapp.data.repository.CurrencyConverterRepositoryImpl
import com.example.currencyconverterapp.data.source.local.dao.CurrencyConverterDao
import com.example.currencyconverterapp.data.source.remote.CurrencyConverterApiService
import com.example.currencyconverterapp.domain.repository.CurrencyConverterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CurrencyConverterRepositoryModule {

    @Provides
    @Singleton
    fun provideIBanValidatorCurrencyConverterRepository(
        currencyConverterApiService: CurrencyConverterApiService,
        currencyConverterDao: CurrencyConverterDao
    ): CurrencyConverterRepository =
        CurrencyConverterRepositoryImpl(currencyConverterApiService, currencyConverterDao)
}