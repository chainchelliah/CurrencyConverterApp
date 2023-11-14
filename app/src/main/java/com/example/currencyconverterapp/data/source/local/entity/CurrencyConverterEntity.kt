package com.example.currencyconverterapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.currencyconverterapp.data.Constants.TABLE_NAME_CONVERTER
import com.example.currencyconverterapp.di.MapConverterType
import com.example.currencyconverterapp.domain.model.CurrencyConverter

@Entity(tableName = TABLE_NAME_CONVERTER)
data class CurrencyConverterEntity(
    val success: Boolean,
    @PrimaryKey
    val base: String,
    @TypeConverters(MapConverterType::class)
    val rates: Map<String, Double>
) {
    fun toData() = CurrencyConverter(success = success, base = base, rates = rates)
}