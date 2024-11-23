package com.example.mango_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.mango_app.utils.CardDesign
import com.example.mango_app.viewmodel.AddMoneyViewModel

@Composable
fun AddMoneyScreen(addMoneyViewModel: AddMoneyViewModel) {
    val cards by addMoneyViewModel.cards.observeAsState(emptyList())
    val amount by addMoneyViewModel.amount.observeAsState("")
    val successMessageVisible by addMoneyViewModel.successMessageVisible.observeAsState(false)
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { cards.size })

    if (cards.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No hay tarjetas disponibles",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 32.dp)
                ) { page ->
                    CardDesign(card = cards[page])
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(cards.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .padding(horizontal = 6.dp)
                                .background(
                                    color = if (index == pagerState.currentPage) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                                    },
                                    shape = CircleShape
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = amount,
                    onValueChange = { addMoneyViewModel.setAmount(it) },
                    label = { Text("Ingrese el monto") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val selectedCardId = cards[pagerState.currentPage].id
                        addMoneyViewModel.setCardId(selectedCardId)
                        addMoneyViewModel.addMoney()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Agregar Dinero")
                }
            }
        }
    }
    // Diálogo de éxito
    if (successMessageVisible) {
        AlertDialog(
            onDismissRequest = { addMoneyViewModel.dismissSuccessMessage() },
            confirmButton = {
                TextButton(onClick = { addMoneyViewModel.dismissSuccessMessage() }) {
                    Text(text = "Cerrar")
                }
            },
            title = { Text(text = "¡Éxito!") },
            text = { Text(text = "El dinero se ha agregado correctamente.") },
        )
    }
}