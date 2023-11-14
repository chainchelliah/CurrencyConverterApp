package com.example.currencyconverterapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencyconverterapp.data.Constants.TABLE_NAME_IBAN
import com.example.currencyconverterapp.domain.model.IBanValidator

@Entity(tableName = TABLE_NAME_IBAN)
data class IBanValidatorEntity(
    @PrimaryKey val accountNumber: String,
    val isValid: Boolean,
    val message: String
) {
    fun toIBanValidator() = IBanValidator(isValid, message = message, accountNumber)
}