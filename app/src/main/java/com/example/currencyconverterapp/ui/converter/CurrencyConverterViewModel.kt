package com.example.currencyconverterapp.ui.converter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterapp.data.source.remote.Resource
import com.example.currencyconverterapp.domain.usecase.IBanValidatorUseCase
import com.example.currencyconverterapp.ui.base.BaseViewModel
import com.example.currencyconverterapp.ui.iban.IBanValidatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrencyConverterViewModel : BaseViewModel() {
}