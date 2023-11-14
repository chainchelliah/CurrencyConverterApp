package com.example.currencyconverterapp.ui.iban

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IBanValidatorScreen(iBanValidatorViewModel: IBanValidatorViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val state = iBanValidatorViewModel.state.value
    val text = remember { mutableStateOf(TextFieldValue("")) }

    if (state.message.isNotBlank()) {
        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(value = text.value,  placeholder = {
            Text("Enter IBAN Id")
        }, onValueChange = { newValue ->
            run {
                text.value = newValue
            }
        })

        Spacer(modifier = Modifier.height(20.dp))

        if (state.isLoading) CircularProgressIndicator() else OutlinedButton(onClick = {
            iBanValidatorViewModel.validateAccountNumber(
                text.value.text
            )
        }) {
            Text("Validate")
        }

    }

}