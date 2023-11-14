package com.example.currencyconverterapp.di.converter

import com.example.currencyconverterapp.domain.repository.CurrencyConverterRepository
import com.example.currencyconverterapp.domain.usecase.CurrencyConverterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CurrencyConverterUseCaseModule {

    @Provides
    @Singleton
    fun provideCurrencyConverterUseCase(repository: CurrencyConverterRepository) =
        CurrencyConverterUseCase(repository)
}