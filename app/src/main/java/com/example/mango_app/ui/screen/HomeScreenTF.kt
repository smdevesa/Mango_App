package com.example.mango_app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.FootScreenBar


@Composable
fun ActionButton(icon: Painter, text: String, color: Color = MaterialTheme.colorScheme.onBackground, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            contentDescription = text,
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(color = color)
        )
    }
}


@Composable
fun HomeScreenTF(
    balance: Double,
    onTransferClick: () -> Unit,
    onDepositClick: () -> Unit,
    onInvestClick: () -> Unit,
    onSeeMyDataClick: () -> Unit,
    onViewAllTransactions: () -> Unit,
    // Parámetros para la NavigationBar
    onClickHome: () -> Unit,
    onClickHistory: () -> Unit,
    onClickData: () -> Unit,
    onClickProfile: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
                //.padding(16.dp), //AGREGAR ESTO Y ME QUEDA UN PEQUENO BORDE ARRIBA ABAJO Y A LOS LADOS QUIZA ES ALGO APRA LOS CELIS
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Saldo disponible
                Text(
                    text = "Saldo disponible",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "$ $balance",
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 32.sp),
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Acciones rápidas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ActionButton(icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24), text = stringResource(id = R.string.transfer), onClick = onTransferClick)
                    ActionButton(icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24), text = stringResource(id = R.string.deposit), onClick = onDepositClick)
                    ActionButton(icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24), text = stringResource(id = R.string.invest), onClick = onInvestClick)
                    ActionButton(icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24), text = stringResource(id = R.string.data), onClick = onSeeMyDataClick)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Historial de transacciones
                Text(
                    text = "Historial reciente",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(16.dp))

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(onClick = onViewAllTransactions) {
                        Text("Ver todas las transacciones")
                    }
                }
            }

            FootScreenBar(
                onClickHome,
                onClickHistory,
                onClickData,
                onClickProfile
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewLightTF() {
    Mango_AppTheme(darkTheme = false, content = {
        HomeScreenTF(
            balance = 1250.75,
            onTransferClick = {},
            onDepositClick = {},
            onInvestClick = {},
            onSeeMyDataClick = {},
            onViewAllTransactions = {},
            // Parámetros para la NavigationBar
            onClickHome = {},
            onClickHistory = {},
            onClickData = {},
            onClickProfile = {}
        )
    })
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewDarkTF() {
    Mango_AppTheme(darkTheme = true, content = {
        HomeScreenTF(
            balance = 1250.75,
            onTransferClick = {},
            onDepositClick = {},
            onInvestClick = {},
            onSeeMyDataClick = {},
            onViewAllTransactions = {},
            // Parámetros para la NavigationBar
            onClickHome = {},
            onClickHistory = {},
            onClickData = {},
            onClickProfile = {}
        )
    })
}
