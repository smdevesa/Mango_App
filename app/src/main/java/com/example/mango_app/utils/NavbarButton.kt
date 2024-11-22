package com.example.mango_app.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme

@Composable
fun NavbarButton(
    text: String,
    icon: Painter,
    size: Int,
    fontSize: Int,
    iconColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onSecondary,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                painter = icon,
                contentDescription = text,
                modifier = Modifier.size(size.dp),
                tint = iconColor // Aplicación del color del ícono
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = fontSize.sp,
                color = textColor // Aplicación del color del texto
            )
        )
    }
}

@Preview
@Composable
fun PreviewNavbarButton() {
    Mango_AppTheme {
        NavbarButton(
            text = "Home",
            icon = painterResource(id = R.drawable.baseline_credit_card_24),
            size = 30,
            fontSize = 16,
            onClick = {}
        )
    }
}