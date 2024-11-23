package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.model.data.Card
import com.example.mango_app.utils.ActionButton
import com.example.mango_app.utils.ButtonMango
import com.example.mango_app.utils.CardDesign
import com.example.mango_app.viewmodel.CardViewModel

@Composable
fun CardsScreen(
    cardViewModel: CardViewModel,
) {
    val cards by cardViewModel.cards.observeAsState(emptyList())
    val isLoading by cardViewModel.isLoading.observeAsState(false)
    var showDialog by remember { mutableStateOf(false) }
    var cardToDelete by remember { mutableStateOf<Card?>(null) }

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center, // Centra verticalmente el contenido
            horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente el contenido
        ) {
            if (isLoading) {
                // Indicador de carga
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            } else {
                if (cards.isEmpty()) {
                    // Mensaje de "No hay tarjetas"
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.no_cards),
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onSurface, // Asegúrate de usar un color contrastante
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    // Lista de tarjetas
                    items(cards) { card ->
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .padding(bottom = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .width(390.dp), // Ancho fijo
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                CardDesign(
                                    card = card,
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                ActionButton(
                                    icon = painterResource(id = R.drawable.baseline_delete_24),
                                    onClick = {
                                       cardToDelete = card
                                        showDialog = true
                                    }

                                )
                            }
                        }
                    }
                }

                // Botón para agregar tarjeta
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        ButtonMango(text = stringResource(id = R.string.add_card))
                    }
                }
            }
        }
    }
    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false},
            title = {Text(text = stringResource(id = R.string.delete_card))},
            text = {Text(text = stringResource(id = R.string.delete_card_message))},
            confirmButton = {
                TextButton(onClick = {
                    cardViewModel.deleteCard(cardToDelete!!.id)
                    showDialog = false
                }) {
                    Text(text = stringResource(id = R.string.delete))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }

        )
    }
}




