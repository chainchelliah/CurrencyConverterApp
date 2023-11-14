package com.example.currencyconverterapp.data.repository

import com.example.currencyconverterapp.data.source.local.dao.IBanValidatorDao
import com.example.currencyconverterapp.data.source.remote.IBanValidatorApiService
import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.data.source.remote.dto.IBanValidatorDto
import com.example.currencyconverterapp.domain.model.IBanValidator
import com.example.currencyconverterapp.domain.repository.IBanValidatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class IBanValidatorRepositoryImpl @Inject constructor(
    private val iBanValidatorApiService: IBanValidatorApiService,
    private val iBanValidatorDao: IBanValidatorDao
) :
    IBanValidatorRepository {

    override fun validateAccount(accountNumber: String): Flow<Resource<IBanValidator>> = flow {
        emit(Resource.Loading())
        try {
            val result = iBanValidatorApiService.getAccountDetails(accountNumber)
            insertData(accountNumber, result)
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

        emit(Resource.Success(getIBanAccountFromDb(accountNumber)))

    }

    private suspend fun insertData(accountNumber: String, result: IBanValidatorDto) {
        iBanValidatorDao.insertAccount(result.toData(accountNumber))
    }

    private suspend fun getIBanAccountFromDb(accountNumber: String): IBanValidator? {
        return iBanValidatorDao.getAccount(accountNumber)?.toIBanValidator()
    }
}