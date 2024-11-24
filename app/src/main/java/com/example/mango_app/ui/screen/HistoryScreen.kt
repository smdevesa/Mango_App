package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.model.Transaction
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.TransactionItem
import com.example.mango_app.utils.TitledCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


// Pantalla de historial
@Composable
fun TransactionHistoryScreen(
    transactions: List<Transaction>,
    onTransactionClick: (Transaction) -> Unit
) {
    Scaffold(
        content = { padding ->
            TitledCard(
                title = stringResource(R.string.transaction_history), // Título centrado
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (transactions.isEmpty()) {
                    // Mostrar mensaje si no hay transacciones
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.no_transactions),
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
                            .padding(16.dp),
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
        }
    )
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
            date = Date()
        ),
        Transaction(
            id = "2",
            description = "Transferencia recibida",
            amount = 200.0,
            date = Date()
        ),
        Transaction(
            id = "3",
            description = "Suscripción mensual",
            amount = -9.99,
            date = Date()
        ),
        Transaction(
            id = "4",
            description = "Reembolso",
            amount = 30.0,
            date = Date()
        )
    )
}
