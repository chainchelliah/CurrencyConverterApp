package com.example.currencyconverterapp.data.source.remote.dto

import com.example.currencyconverterapp.data.source.local.entity.IBanValidatorEntity
import com.example.currencyconverterapp.domain.model.IBanValidator
import com.squareup.moshi.Json

data class IBanValidatorDto(
    @Json(name = "valid")
    val isValid: Boolean,
    @Json(name = "message")
    val message: String
) {
    fun toData(accountNumber: String): IBanValidatorEntity {
        return IBanValidatorEntity(accountNumber, isValid, message)
    }
}