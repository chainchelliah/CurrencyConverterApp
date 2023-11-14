package com.example.currencyconverterapp.domain.model

data class CurrencyConverter(val success : Boolean, val base : String, val rates : Map<String, Double>) {
}

