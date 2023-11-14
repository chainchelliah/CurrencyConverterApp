package com.example.currencyconverterapp.data.source.remote.dto

import com.example.currencyconverterapp.data.source.local.entity.CurrencyConverterEntity

data class CurrencyConverterDto(val success : Boolean, val base : String, val rates : Map<String, Double>) {
    fun toCurrencyEntity() = CurrencyConverterEntity(base = base, success = success, rates = rates)
}