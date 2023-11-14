package com.example.currencyconverterapp.di.iban

import com.example.currencyconverterapp.data.repository.IBanValidatorRepositoryImpl
import com.example.currencyconverterapp.data.source.remote.IBanValidatorApiService
import com.example.currencyconverterapp.domain.repository.IBanValidatorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IBanValidatorRepositoryModule {

    @Provides
    @Singleton
    fun provideIBanValidatorRepository(iBanValidatorApiService: IBanValidatorApiService) : IBanValidatorRepository =
        IBanValidatorRepositoryImpl(iBanValidatorApiService)
}