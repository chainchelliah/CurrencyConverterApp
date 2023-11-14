package com.example.currencyconverterapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.currencyconverterapp.data.source.local.dao.CurrencyConverterDao
import com.example.currencyconverterapp.data.source.local.dao.IBanValidatorDao
import com.example.currencyconverterapp.data.source.local.entity.CurrencyConverterEntity
import com.example.currencyconverterapp.data.source.local.entity.IBanValidatorEntity
import com.example.currencyconverterapp.di.MapConverterType

@Database(entities = [IBanValidatorEntity::class, CurrencyConverterEntity::class], version = 1, exportSchema = false)
@TypeConverters(MapConverterType::class)
abstract class CurrencyAppDatabase : RoomDatabase(){

    abstract fun getIBanValidatorDao() : IBanValidatorDao
    abstract fun getCurrencyConverterDao() : CurrencyConverterDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyAppDatabase? = null

        fun getDatabase(context: Context): CurrencyAppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyAppDatabase::class.java,
                    "currency_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}