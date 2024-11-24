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
    val isLinkValid by payViewModel.isLinkValid.observeAsState(true) // Inicialmente válido para evitar mostrar error antes de escribir

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
            // Campo de texto para ingresar el enlace
            CustomTextField(
                value = paymentLink,
                onValueChange = {
                    paymentLink = it
                    if (it.isNotEmpty()) {
                        payViewModel.validatePaymentLink(it) // Valida automáticamente al escribir
                    }
                },
                label = stringResource(id = R.string.payment_link)
            )

            // Mostrar error debajo del campo si el enlace es inválido
            if (!isLinkValid && paymentLink.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.invalid_payment_link),
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón deshabilitado si el enlace es inválido
            Button(
                onClick = {
                    navController.navigate("paymentDetails/$paymentLink")
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isLinkValid // Habilitado solo si el enlace es válido
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}
