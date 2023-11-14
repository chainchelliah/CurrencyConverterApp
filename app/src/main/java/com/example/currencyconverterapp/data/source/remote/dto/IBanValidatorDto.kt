package com.example.currencyconverterapp.data.source.remote.dto

import com.example.currencyconverterapp.domain.model.IBanValidator
import com.squareup.moshi.Json

data class IBanValidatorDto(
    @Json(name = "valid")
    val isValid: Boolean,
    @Json(name = "message")
    val message : String
) {
    fun toData(): IBanValidator {
        return IBanValidator(isValid, message)
    }
}