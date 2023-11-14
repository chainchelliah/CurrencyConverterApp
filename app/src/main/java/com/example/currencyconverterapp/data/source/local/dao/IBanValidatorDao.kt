package com.example.currencyconverterapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverterapp.data.Constants.TABLE_NAME_IBAN
import com.example.currencyconverterapp.data.source.local.entity.IBanValidatorEntity

@Dao
interface IBanValidatorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: IBanValidatorEntity)

    @Query("SELECT * FROM $TABLE_NAME_IBAN WHERE accountNumber = :accountNumberInput")
    suspend fun getAccount(accountNumberInput : String): IBanValidatorEntity?

    @Query("DELETE FROM $TABLE_NAME_IBAN")
    suspend fun deleteAll()
}