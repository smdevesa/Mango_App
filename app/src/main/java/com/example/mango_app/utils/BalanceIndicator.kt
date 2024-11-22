package com.example.mango_app.utils

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

    Box(
        modifier = Modifier
            .fillMaxWidth() // Ajusta el tamaño al 90% del ancho de la pantalla
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(16.dp))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start, // Alineación izquierda
            verticalArrangement = Arrangement.Center
        ) {
            // Texto "Balance Actual" en la esquina superior izquierda
            Text(
                text = "Balance Actual",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre el título y el balance

            // Balance con formato en el centro y letra en negrita
            Text(
                text = formattedBalance,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 32.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
@Preview
fun PreviewBalanceIndicator() {
    Mango_AppTheme (darkTheme = false){
        BalanceIndicator(balance = 1000.0)
    }
}
