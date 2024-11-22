package com.example.mango_app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme

@Composable
fun FootScreenBar(
    onClickHome: () -> Unit, //SACAR TODAS ESTAS Y ASIGNARLAS DIRECTAMENTE AL ACTION BUTTON
    onClickHistory: () -> Unit,
    onClickCard: () -> Unit,
    onClickProfile: () -> Unit,
    selectedRoute: String = ""
    ) {
Row(
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.secondary)
        .padding(vertical = 8.dp)

) {
    NavbarButton(
        icon = painterResource(id = R.drawable.baseline_home_24),
        text = "Inicio",
        iconColor = if(selectedRoute == "home") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary,
        size = 72,
        fontSize = 12,
        onClick = { onClickHome()}
    )
    NavbarButton(
        icon = painterResource(id = R.drawable.baseline_find_in_page_24),
        text = "Historial",
        iconColor = if(selectedRoute == "history") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary,
        size = 72,
        fontSize = 12,
        onClick = { onClickHistory() }
    )
    NavbarButton(
        icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
        text = "",
        iconColor = MaterialTheme.colorScheme.primary,
        size = 72,
        fontSize = 12,
        onClick = { /* No hace nada, solo para el Preview */ }
    )
    NavbarButton(
        icon = painterResource(id = R.drawable.baseline_credit_card_24),
        text = "Tarjetas",
        iconColor = if(selectedRoute == "card") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary,
        size = 72,
        fontSize = 12,
        onClick = { onClickCard() }
    )
    NavbarButton(
        icon = painterResource(id = R.drawable.baseline_account_circle_24),
        text = "Perfil",
        iconColor = if(selectedRoute == "profile") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary,
        size = 72,
        fontSize = 12,
        onClick = { onClickProfile() }
    )
}
}



@Preview(showBackground = true)
@Composable
fun NavigationBarLightPreview() {
    Mango_AppTheme(darkTheme = false, content = {
        FootScreenBar(
            onClickHome = { /* No hace nada, solo para el Preview */ },
            onClickHistory = { /* No hace nada, solo para el Preview */ },
            onClickCard = { /* No hace nada, solo para el Preview */ },
            onClickProfile = { /* No hace nada, solo para el Preview */ },
            selectedRoute = "Perfil"
        )
    })
}

@Preview(showBackground = true)
@Composable
fun NavigationBarDarkPreview() {
    Mango_AppTheme(darkTheme = true, content = {
        FootScreenBar(
            onClickHome = { /* No hace nada, solo para el Preview */ },
            onClickHistory = { /* No hace nada, solo para el Preview */ },
            onClickCard = { /* No hace nada, solo para el Preview */ },
            onClickProfile = { /* No hace nada, solo para el Preview */ },
            selectedRoute = "Inicio"
        )
    })
}