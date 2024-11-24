package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.model.Transaction
import com.example.mango_app.utils.TransactionItem
import com.example.mango_app.utils.TitledCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun TransactionHistoryScreen(
    transactions: List<Transaction>,
    onTransactionClick: (Transaction) -> Unit
) {
    Scaffold(
        content = { padding ->
            TitledCard(
                title = stringResource(R.string.transaction_history),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                if (transactions.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
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

fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    return formatter.format(date)
}


fun fakeTransactions(): List<Transaction> {
    return listOf(
        Transaction(
            id = "1",
            description = "Cobro de sueldo",
            amount = 50.25,
            date = Date()
        ),
        Transaction(
            id = "2",
            description = "Transferencia de Juan",
            amount = 20.00,
            date = Date()
        ),
        Transaction(
            id = "3",
            description = "Pago a Devesa",
            amount = -35.00,
            date = Date()
        )
    )
}
