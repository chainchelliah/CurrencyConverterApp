package com.example.currencyconverterapp.data.repository

import com.example.currencyconverterapp.data.source.remote.IBanValidatorApiService
import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.domain.model.IBanValidator
import com.example.currencyconverterapp.domain.repository.IBanValidatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class IBanValidatorRepositoryImpl @Inject constructor(private val iBanValidatorApiService: IBanValidatorApiService) :
    IBanValidatorRepository {

    private val validAccountMutex = Mutex()

    private var validAccount: IBanValidator? = null

    override fun validateAccount(accountNumber: String): Flow<Resource<IBanValidator>> = flow {
        emit(Resource.Loading())
        try {
            val result = iBanValidatorApiService.validateAccount(accountNumber)

            validAccountMutex.withLock {
                validAccount = result.toData()
                emit(Resource.Success(validAccount))
            }

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
        // single source of truth we will emit data from db only and not directly from remote
        validAccountMutex.withLock {
            if (validAccount != null)
                emit(Resource.Success(validAccount))
        }
    }
}