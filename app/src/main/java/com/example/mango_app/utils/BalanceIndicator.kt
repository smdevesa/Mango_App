package com.example.mango_app.utils

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.ui.theme.Mango_AppTheme
import java.text.DecimalFormat

@Composable
fun BalanceIndicator(
    balance: Double
) {
    // Formato del balance con el signo "$" y dos decimales
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

@Composable
@Preview
fun PreviewBalanceIndicator() {
    Mango_AppTheme (darkTheme = false){
        BalanceIndicator(balance = 1000.0)
    }
}
