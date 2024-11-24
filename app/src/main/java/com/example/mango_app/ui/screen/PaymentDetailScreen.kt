package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mango_app.R
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.viewmodel.PayViewModel

@Composable
fun PaymentDetailScreen(
    linkUuid: String,
    navController: NavHostController,
    payViewModel: PayViewModel
) {
    val amount by payViewModel.amount.observeAsState("")
    val cards by payViewModel.cards.observeAsState(emptyList())
    val successMessageVisible by payViewModel.successMessageVisible.observeAsState(false)
    val showSuccessAlert = remember { mutableStateOf(false) }

    // Cargar detalles del pago al iniciar la pantalla
    LaunchedEffect(linkUuid) {
        payViewModel.fetchPaymentDetails(linkUuid)
    }

    // Mostrar alerta de éxito si el pago fue exitoso
    LaunchedEffect(successMessageVisible) {
        if (successMessageVisible) {
            showSuccessAlert.value = true
        }
    }

    TitledCard(
        title = stringResource(id = R.string.payment_details),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(text = "Monto a pagar: $amount", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    payViewModel.payWithBalance(linkUuid)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.pay_with_balance))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = R.string.pay_with_card))

            cards.forEach { card ->
                Button(
                    onClick = {
                        payViewModel.payWithCard(linkUuid, card.id)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Pagar con ${card.number}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

    // Mostrar alerta de éxito al completar el pago
    if (showSuccessAlert.value) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = {
                showSuccessAlert.value = false
                payViewModel.resetSuccessMessage() // Restablece el estado en el ViewModel
                navController.navigate("home") // Navega a la pantalla principal
            },
            title = { Text(text = stringResource(id = R.string.success)) },
            text = { Text(text = "Pago realizado con éxito") },
            confirmButton = {
                androidx.compose.material3.TextButton(
                    onClick = {
                        showSuccessAlert.value = false
                        payViewModel.resetSuccessMessage() // Restablece el estado en el ViewModel
                        navController.navigate("home") // Navega a la pantalla principal
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}
