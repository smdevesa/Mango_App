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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
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

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp), // Padding interno
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre elementos
        ) {
            if (cards.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center // Centra el contenido
                    ) {
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 18.dp),
                            color = MaterialTheme.colorScheme.onSurface, // Asegúrate de usar un color contrastante
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                items(cards) { card ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CardDesign(card = card)
                        Spacer(modifier = Modifier.width(16.dp))
                        ActionButton(
                            icon = painterResource(id = R.drawable.baseline_delete_24),
                            onClick = { cardViewModel.deleteCard(card.id) }
                        )
                    }
                }
            }

            item {
                ButtonMango(text = stringResource(id = R.string.add_card))
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Indicador de carga (puedes usar CircularProgressIndicator o cualquier otro widget)
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp)
                )
            }
        }

    }
}
