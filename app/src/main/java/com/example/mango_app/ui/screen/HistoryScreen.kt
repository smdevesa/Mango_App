package com.example.wallet.ui.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mango_app.ui.screen.HomeScreen
import com.example.mango_app.ui.theme.Mango_AppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Clase Transaction
data class Transaction(
    val id: String,
    val description: String,
    val amount: Double,
    val currency: String,
    val date: Date
)

// Pantalla de historial
@Composable
fun TransactionHistoryScreen(
    transactions: List<Transaction>,
    onTransactionClick: (Transaction) -> Unit
) {
    Scaffold(
        content = { padding ->
            if (transactions.isEmpty()) {
                // Mostrar mensaje si no hay transacciones
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay transacciones disponibles",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            } else {
                // Lista de transacciones
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(transactions) { transaction ->
                        TransactionItem(
                            transaction = transaction,
                            onClick = { onTransactionClick(transaction) }
                        )
                    }
                }
            }
        }
    )
}

// Item de transacción
@Composable
fun TransactionItem(
    transaction: Transaction,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícono según el tipo de transacción
            Icon(
                imageVector = if (transaction.amount > 0) Icons.Default.Add else Icons.Default.AddCircle,
                contentDescription = null,
                tint = if (transaction.amount > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Detalles de la transacción
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.description,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatDate(transaction.date),
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Monto de la transacción
            Text(
                text = "${if (transaction.amount > 0) "+" else ""}${transaction.amount} ${transaction.currency}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = if (transaction.amount > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.End
            )
        }
    }
}

// Función auxiliar para formato de fecha
fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    return formatter.format(date)
}


@Preview(showBackground = true)
@Composable
fun TransactionHistoryPreview() {
    Mango_AppTheme(darkTheme = false, content = {
        TransactionHistoryScreen(
            transactions = fakeTransactions(),
            onTransactionClick = { transaction ->
                // Acción al hacer clic en una transacción
                println("Clicked on transaction: ${transaction.description}")
            }
        )
    })
}

// Datos de prueba para el preview
fun fakeTransactions(): List<Transaction> {
    return listOf(
        Transaction(
            id = "1",
            description = "Pago en Supermercado",
            amount = -50.25,
            currency = "USD",
            date = Date()
        ),
        Transaction(
            id = "2",
            description = "Transferencia recibida",
            amount = 200.0,
            currency = "USD",
            date = Date()
        ),
        Transaction(
            id = "3",
            description = "Suscripción mensual",
            amount = -9.99,
            currency = "USD",
            date = Date()
        ),
        Transaction(
            id = "4",
            description = "Reembolso",
            amount = 30.0,
            currency = "USD",
            date = Date()
        )
    )
}
