package com.example.currencyconverterapp.domain.usecase

import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.domain.model.CurrencyConverter
import com.example.currencyconverterapp.domain.repository.CurrencyConverterRepository
import kotlinx.coroutines.flow.Flow

class CurrencyConverterUseCase(private val currencyConverterRepository: CurrencyConverterRepository) {

    operator fun invoke(): Flow<Resource<CurrencyConverter>> =
        currencyConverterRepository.getCurrencies()

}