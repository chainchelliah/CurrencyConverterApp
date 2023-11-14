package com.example.currencyconverterapp.domain.repository

import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.domain.model.IBanValidator
import kotlinx.coroutines.flow.Flow

interface IBanValidatorRepository {

    fun validateAccount(accountNumber: String): Flow<Resource<IBanValidator>>
}