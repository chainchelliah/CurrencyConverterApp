package com.example.currencyconverterapp.domain.usecase

import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.domain.model.IBanValidator
import com.example.currencyconverterapp.domain.repository.IBanValidatorRepository
import kotlinx.coroutines.flow.Flow

class IBanValidatorUseCase(private val iBanValidatorRepository: IBanValidatorRepository) {

    operator fun invoke(accountNumber: String): Flow<Resource<IBanValidator>> =
        iBanValidatorRepository.validateAccount(accountNumber)

}