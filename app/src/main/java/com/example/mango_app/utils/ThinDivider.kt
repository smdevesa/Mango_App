package com.example.mango_app.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ThinDivider() {
    HorizontalDivider(
        color = MaterialTheme.colorScheme.outline, // Color de la línea (puedes elegir otro color)
        thickness = 1.dp, // Grosor de la línea
        modifier = Modifier
            .fillMaxWidth() // La línea ocupará todo el ancho disponible
            .padding(vertical = 8.dp) // Espaciado opcional alrededor de la línea
    )
}
