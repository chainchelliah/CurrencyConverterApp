package com.example.currencyconverterapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverterapp.data.Constants
import com.example.currencyconverterapp.data.source.local.entity.CurrencyConverterEntity
import com.example.currencyconverterapp.data.source.local.entity.IBanValidatorEntity

@Dao
interface CurrencyConverterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencies(currency: CurrencyConverterEntity)

    @Query("SELECT * FROM ${Constants.TABLE_NAME_CONVERTER} WHERE base = :baseCode")
    suspend fun getCurrencyByCode(baseCode : String): CurrencyConverterEntity?

    @Query("DELETE FROM ${Constants.TABLE_NAME_CONVERTER}")
    suspend fun deleteAll()
}

