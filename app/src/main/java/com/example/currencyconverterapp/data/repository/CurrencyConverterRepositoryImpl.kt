package com.example.currencyconverterapp.data.repository

import android.util.Log
import com.example.currencyconverterapp.data.source.local.dao.CurrencyConverterDao
import com.example.currencyconverterapp.data.source.remote.CurrencyConverterApiService
import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.data.source.remote.dto.CurrencyConverterDto
import com.example.currencyconverterapp.domain.model.CurrencyConverter
import com.example.currencyconverterapp.domain.repository.CurrencyConverterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrencyConverterRepositoryImpl@Inject constructor(
    private val currencyConverterApiService: CurrencyConverterApiService,
    private val currencyConverterDao: CurrencyConverterDao
) :
    CurrencyConverterRepository {

    override fun getCurrencies(): Flow<Resource<CurrencyConverter>> = flow {
        emit(Resource.Loading())
        try {
            val result = currencyConverterApiService.getCurrencies("KWD")
            insertData(result)
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }

        emit(Resource.Success(getCurrency()))

    }

    private suspend fun insertData(result: CurrencyConverterDto) {
        Log.d("CurrentyApp", " The resulted data is ${result.rates.map { "${it.key}: ${it.value}" }.joinToString(", ")}")
        currencyConverterDao.insertCurrencies(result.toCurrencyEntity())
    }

    private suspend fun getCurrency(): CurrencyConverter? {
        return currencyConverterDao.getCurrencyByCode("KWD")?.toData()
    }
}