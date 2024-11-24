package com.example.mango_app.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

@Composable
fun BalanceIndicator(
    balance: Double
) {
    val formattedBalance = DecimalFormat("$#,##0.00").format(balance)
    Text(
        text = formattedBalance,
        style = MaterialTheme.typography.titleMedium.copy(
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .padding(16.dp)
    )
}
