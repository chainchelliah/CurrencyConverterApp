package com.example.currencyconverterapp.ui.converter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterapp.data.source.local.entity.CurrencyConverterEntity
import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.domain.usecase.CurrencyConverterUseCase
import com.example.currencyconverterapp.domain.usecase.IBanValidatorUseCase
import com.example.currencyconverterapp.ui.base.BaseViewModel
import com.example.currencyconverterapp.ui.iban.IBanValidatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(private val currencyConverterUseCase: CurrencyConverterUseCase) :
    BaseViewModel() {
    private val _state = mutableStateOf(CurrencyConverterState())
    val state: State<CurrencyConverterState> = _state

    init {
        getCurrencies()
    }
    private fun getCurrencies() {
        viewModelScope.launch {
            currencyConverterUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            success = result.data?.success ?: false,
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            success = result.data?.success ?: false,
                            isLoading = false,
                            data  = result.data
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            success  = false,
                            isLoading = false
                        )

                    }

                    else -> {}
                }
            }.launchIn(this)
        }
    }
}