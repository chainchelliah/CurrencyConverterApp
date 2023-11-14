package com.example.currencyconverterapp.di.iban

import com.example.currencyconverterapp.domain.repository.IBanValidatorRepository
import com.example.currencyconverterapp.domain.usecase.IBanValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IBanValidatorUsecaseModule {

    @Provides
    @Singleton
    fun provideIBanValidatorUseCase(repository: IBanValidatorRepository) = IBanValidatorUseCase(repository)
}