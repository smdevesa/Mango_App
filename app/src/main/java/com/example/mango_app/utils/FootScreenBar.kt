package com.example.mango_app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme

@Composable
fun FootScreenBar(
    onClickHome: () -> Unit, //SACAR TODAS ESTAS Y ASIGNARLAS DIRECTAMENTE AL ACTION BUTTON
    onClickHistory: () -> Unit,
    onClickCard: () -> Unit,
    onClickProfile: () -> Unit,
    ) {
Row(
    horizontalArrangement = Arrangement.SpaceEvenly,
    modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.secondary)

) {
    ActionButton(
        icon = painterResource(id = R.drawable.baseline_home_24),
        text = "Inicio",
        color = Color.White,
        onClick = { onClickHome()}
    )
    ActionButton(
        icon = painterResource(id = R.drawable.baseline_find_in_page_24),
        text = "Historial",
        color = Color.White,
        onClick = { onClickHistory() }
    )
    ActionButton(
        icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
        text = "",
        color = Color.White,
        onClick = { /* No hace nada, solo para el Preview */ }
    )
    ActionButton(
        icon = painterResource(id = R.drawable.baseline_credit_card_24),
        text = stringResource(id = R.string.card),
        color = Color.White,
        onClick = { onClickCard() }
    )
    ActionButton(
        icon = painterResource(id = R.drawable.baseline_account_circle_24),
        text = "Perfil",
        color = Color.White,
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
            onClickProfile = { /* No hace nada, solo para el Preview */ }
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
            onClickProfile = { /* No hace nada, solo para el Preview */ }
        )
    })
}