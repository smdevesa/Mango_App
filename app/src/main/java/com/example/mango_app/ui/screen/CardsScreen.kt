package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.model.data.Card
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.ActionButton
import com.example.mango_app.utils.ButtonMango
import com.example.mango_app.utils.CardDesign
import com.example.mango_app.utils.FootScreenBar
import com.example.mango_app.utils.TopScreenBar

@Composable
fun CardsScreen(
    onClickHome: () -> Unit,
    onClickHistory: () -> Unit,
    onClickData: () -> Unit,
    onClickProfile: () -> Unit
) {
    // Tarjetas hardcodeadas
    val cards = listOf(
        Card(
            id = 1,
            cardNumber = "1234567890123456",
            type = "Debit",
            fullName = "balbo",
            expirationDate = "12/24",
            cvv = "123"
        ),
        Card(
            id = 2,
            cardNumber = "9876543210987654",
            type = "Credit",
            fullName = "salini",
            expirationDate = "06/25",
            cvv = "456"
        ),
        Card(
            id = 3,
            cardNumber = "1111222233334444",
            type = "Debit",
            fullName = "Alice cobayo",
            expirationDate = "11/23",
            cvv = "789"
        )
    )

    Scaffold(
        topBar = { TopScreenBar(title = "Mis Tarjetas") },
        bottomBar = {
            FootScreenBar(
                onClickHome,
                onClickHistory,
                onClickData,
                onClickProfile
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp), // Padding interno
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre elementos
        ) {
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
                        icon = painterResource(id = R.drawable.baseline_delete_24), // Esta línea ahora es válida
                        onClick = { /* acción de borrar la tarjeta */ }
                    )
                }
            }

            item {
                ButtonMango(text = stringResource(id = R.string.add_card))
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun CardsScreenPreviewLightTF() {
    Mango_AppTheme(darkTheme = false, content = {
        CardsScreen(onClickHome = {},
            onClickHistory = {},
            onClickData = {},
            onClickProfile = {})
    })
}
