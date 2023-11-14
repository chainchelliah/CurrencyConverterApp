package com.example.currencyconverterapp.ui.iban

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.domain.usecase.IBanValidatorUseCase
import com.example.currencyconverterapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IBanValidatorViewModel @Inject constructor(private val iBanValidatorUseCase: IBanValidatorUseCase) :
    BaseViewModel() {

    private val _state = mutableStateOf(IBanValidatorState())
    val state: State<IBanValidatorState> = _state

    fun validateAccountNumber(accountNUmber: String) {
        viewModelScope.launch {
            iBanValidatorUseCase.invoke(accountNUmber).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isValid = result.data?.valid ?: false,
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isValid = result.data?.valid ?: false,
                            isLoading = false,
                            message = result.data?.message ?: ""
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isValid = false,
                            isLoading = false,
                            message = "IBAN number is not valid"
                        )

                    }

                    else -> {}
                }
            }.launchIn(this)
        }
    }
}