package com.example.currencyconverterapp.ui.iban

data class IBanValidatorState(
    val isValid: Boolean = false,
    val isLoading: Boolean = false,
    val message: String = ""
)