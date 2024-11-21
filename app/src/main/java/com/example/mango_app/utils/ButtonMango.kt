package com.example.mango_app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ButtonMango(
    text : String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Asegura que el contenido dentro del Box se centra horizontalmente
            .padding(vertical = 16.dp) // Espaciado vertical
            .wrapContentWidth(Alignment.CenterHorizontally) // Centra el Box horizontalmente
            .clip(RoundedCornerShape(12.dp)) // Opcional: esquinas redondeadas
            .background(color = MaterialTheme.colorScheme.primary) // Fondo del botón
            .clickable { /* acción de agregar tarjeta */ },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.padding(12.dp) // Padding interno para el texto
        )
    }
}