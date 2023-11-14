package com.example.currencyconverterapp.ui.converter

import com.example.currencyconverterapp.domain.model.CurrencyConverter

data class CurrencyConverterState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val data: CurrencyConverter? = null
)