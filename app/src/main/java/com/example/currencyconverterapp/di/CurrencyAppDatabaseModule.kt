package com.example.currencyconverterapp.di

import android.app.Application
import com.example.currencyconverterapp.data.source.local.CurrencyAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CurrencyAppDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = CurrencyAppDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun provideIBanValidatorDao(database: CurrencyAppDatabase) =
        database.getIBanValidatorDao()

    @Singleton
    @Provides
    fun provideCurrencyConverterDao(database: CurrencyAppDatabase) =
        database.getCurrencyConverterDao()
}