package com.example.mango_app.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mango_app.R
import com.example.mango_app.utils.CustomTextField
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.viewmodel.PayViewModel

@Composable
fun PayScreen(payViewModel: PayViewModel, navController: NavController) {
    var paymentLink by remember { mutableStateOf("") }
    val isLinkValid by payViewModel.isLinkValid.observeAsState(false)
    val showAlert = remember { mutableStateOf(false) }

    TitledCard(
        title = stringResource(id = R.string.pay),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomTextField(
                value = paymentLink,
                onValueChange = { paymentLink = it },
                label = stringResource(id = R.string.payment_link)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (paymentLink.isNotEmpty()) {
                        payViewModel.validatePaymentLink(paymentLink)
                    }


                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.next))

                // Utilizar LaunchedEffect para manejar la navegación de forma asincrónica
                LaunchedEffect(isLinkValid) {
                    if (isLinkValid) {
                        // Navegar solo cuando el link sea válido
                        navController.navigate("paymentDetails/$paymentLink")
                    } else if(paymentLink.isNotEmpty()) {
                        // Mostrar el alert dialog solo si el link no es vacío y no es válido
                        showAlert.value = true
                    }
                }
            }


        }
    }

    // Mostrar el AlertDialog cuando el link es inválido
    if (showAlert.value) {
        AlertDialog(
            onDismissRequest = { showAlert.value = false },
            title = { Text(text = "Error") },
            text = { Text(text = stringResource(id = R.string.invalid_payment_link)) },
            confirmButton = {
                TextButton(
                    onClick = { showAlert.value = false }
                ) {
                    Text(text = "Ok")
                }
            }
        )
    }
}