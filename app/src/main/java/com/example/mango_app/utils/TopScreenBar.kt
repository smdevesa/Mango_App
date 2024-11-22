package com.example.mango_app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme


@Composable
fun TopScreenBar(
    title: String,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Espacio para posicionar el ícono al 20% del ancho
            Box(
                modifier = Modifier
                    .weight(0.2f) // Ocupa el 20% del espacio horizontal
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { onClick() }
                        .size(28.dp) // Tamaño del ícono
                )
            }

            // Espacio para centrar el título al 50% del ancho
            Box(
                modifier = Modifier
                    .weight(0.6f) // Ocupa el 60% del espacio horizontal
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp // Ajusta el tamaño según el diseño
                )
            }

            // Espacio vacío para mantener el equilibrio visual
            Spacer(modifier = Modifier.weight(0.2f)) // Ocupa el 20% restante del ancho
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopScreenBarLightPreview() {
    Mango_AppTheme(darkTheme = false, content = {
        TopScreenBar("Prueba")
    })
}

@Preview(showBackground = true)
@Composable
fun TopScreenBarDarkPreview() {
    Mango_AppTheme(darkTheme = true, content = {
        TopScreenBar("Prueba")

    })
}