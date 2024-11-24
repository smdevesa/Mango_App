package com.example.mango_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.model.data.Card
import com.example.mango_app.utils.ButtonMango
import com.example.mango_app.utils.CardDesign
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.viewmodel.CardViewModel

@Composable
fun CardsScreen(
    cardViewModel: CardViewModel,
) {
    val cards by cardViewModel.cards.observeAsState(emptyList())
    val isLoading by cardViewModel.isLoading.observeAsState(false)
    var showDialog by remember { mutableStateOf(false) }
    var cardToDelete by remember { mutableStateOf<Card?>(null) }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { cards.size })

    TitledCard(
        title = stringResource(id = R.string.cards_title), // Título de la tarjeta
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Indicador de carga
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(48.dp))
                }
            } else {
                // Mostrar carrusel si hay tarjetas
                if(cards.isEmpty()){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                if (cards.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->
                            CardDesign(card = cards[page])
                        }
                    }

                    // Indicador de páginas
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Texto de eliminar
                        TextButton(
                            onClick = {
                                cardToDelete = if (cards.isNotEmpty()) cards[pagerState.currentPage] else null
                                showDialog = true
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.delete_card),
                                color = MaterialTheme.colorScheme.onError,
                                style = MaterialTheme.typography.titleMedium
                            )

                        }

                        // Botón para agregar tarjeta
                        ButtonMango(
                            text = stringResource(id = R.string.add_card),
                        )
                    }
                } else {
                    // Mensaje de "No hay tarjetas"
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                        // Botón para agregar tarjeta
                        ButtonMango(
                            text = stringResource(id = R.string.add_card),
                        )

                }
            }

        }
    }

    // Diálogo de confirmación para eliminar tarjeta
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(id = R.string.delete_card)) },
            text = { Text(text = stringResource(id = R.string.delete_card_message)) },
            confirmButton = {
                TextButton(onClick = {
                    cardToDelete?.let { cardViewModel.deleteCard(it.id) }
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
