package com.example.currencyconverterapp.domain.repository

import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.domain.model.CurrencyConverter
import kotlinx.coroutines.flow.Flow

interface CurrencyConverterRepository {
    fun getCurrencies() : Flow<Resource<CurrencyConverter>>
}