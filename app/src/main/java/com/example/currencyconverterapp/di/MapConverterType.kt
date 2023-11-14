package com.example.currencyconverterapp.di

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MapConverterType {
    @TypeConverter
    fun fromMapType(currency: String): Map<String, Double>? {
        val type = Types.newParameterizedType(
            MutableMap::class.java,
            String::class.java,
            Double::class.javaObjectType
        )
        return Moshi.Builder().build().adapter<Map<String, Double>>(type).fromJson(currency)
    }

    @TypeConverter
    fun fromString(map: Map<String, Double>): String {
        val type = Types.newParameterizedType(
            MutableMap::class.java,
            String::class.java,
            Double::class.javaObjectType
        )
        return Moshi.Builder().build().adapter<Map<String, Double>>(type).toJson(map)
    }
}