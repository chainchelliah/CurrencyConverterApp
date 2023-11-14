package com.example.currencyconverterapp.ui.converter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CurrencyConverterScreen(converterViewModel: CurrencyConverterViewModel = hiltViewModel()) {
    val state = converterViewModel.state.value


    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) CircularProgressIndicator()
        else {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),

                ) {
                Text(
                    "Rates again KWD",
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(" ${state.data?.rates?.map { "${it.key}: ${it.value}" }?.joinToString("\n")}")
            }
        }
    }

}