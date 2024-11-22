package com.example.mango_app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme

@Composable
fun ActionButton(
    icon: Painter,
    text: String = "",
    fontSize: TextUnit = TextUnit.Unspecified,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit,
    size: Dp = 75.dp, // Tamaño del círculo,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        // Círculo con ícono
        Box(
            modifier = Modifier
                .size(size) // Tamaño del círculo
                .background(color = MaterialTheme.colorScheme.background, shape = CircleShape) // Fondo redondo
                .border(
                    width = 1.5.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = CircleShape
                ) // Borde
                .clickable(onClick = onClick), // Acción al clic
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = text,
                modifier = Modifier.size(40.dp), // Tamaño del ícono
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Texto debajo del círculo
        if (text.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre círculo y texto
            Text(
                text = text,
                fontSize = fontSize,
                color = textColor,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 4.dp) // Opcional: para manejar textos largos
            )
        }
    }
}


@Preview
@Composable
fun PreviewActionButtonLight() {
    Mango_AppTheme(darkTheme = false) {
        ActionButton(
            icon = painterResource(id = R.drawable.baseline_credit_card_24),
            text = "Tarjeta",
            fontSize = 12.sp,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewActionButtonDark() {
    Mango_AppTheme(darkTheme = true) {
        ActionButton(
            icon = painterResource(id = R.drawable.baseline_credit_card_24),
            text = "Tarjeta",
            fontSize = 12.sp,
            onClick = {}
        )
    }
}

