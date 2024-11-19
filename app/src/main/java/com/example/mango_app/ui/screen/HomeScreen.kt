/*package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.runtime.Composable
/*

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Bienvenido a MANGO$!")

        Text(text = "Saldo disponible: $100.00")

        Button(onClick = { navController.navigate("payment") }) {
            Text(text = "Ir a pagos")
        }

        Button(onClick = { navController.navigate("transactions") }) {
            Text(text = "Ver transacciones")
        }
    }
}
*/

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mango_app.ui.theme.Mango_AppTheme
import androidx.compose.foundation.Canvas


import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    accountBalance: Float,
    investedBalance: Float,
    onDepositClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    onTransferClick: () -> Unit,
    onInvestClick: () -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gráfico de Dona
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DonutChart(
                        backgroundColor = Color.Blue,
                        sliceColor = Color.Blue,
                        slicePercentage = 1f // 75% del círculo
                    )

                    // Texto central dentro de la dona
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "$${accountBalance}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Disponible",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botones de acción
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ActionButton(
                        text = stringResource(id = R.string.transfer),
                        icon = Icons.Default.Add,
                        onClick = onDepositClick
                    )
                    ActionButton(
                        text = stringResource(id = R.string.withdraw),
                        icon = Icons.Default.Add,
                        onClick = onWithdrawClick
                    )
                    ActionButton(
                        text = stringResource(id = R.string.deposit),
                        icon = Icons.Outlined.AccountBox,
                        onClick = onTransferClick
                    )
                    ActionButton(
                        text = stringResource(id = R.string.invest),
                        icon = Icons.Filled.AddCircle,
                        onClick = onInvestClick
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Píldoras de información
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoPill(
                        label = stringResource(id = R.string.available_balance),
                        value = "$${accountBalance}"
                    )
                    InfoPill(
                        label = stringResource(id = R.string.invested_balance),
                        value = "$${investedBalance}"
                    )
                }
            }
        }
    )
}


@Composable
fun InfoPill(label: String, value: String) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = CircleShape,
        shadowElevation = 4.dp,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewLight() {
    Mango_AppTheme(darkTheme = false, content = {
        HomeScreen(
            accountBalance = 1500f,
            investedBalance = 500f,
            onDepositClick = {},
            onWithdrawClick = {},
            onTransferClick = {},
            onInvestClick = {}
        )
    }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreviewDark() {
    Mango_AppTheme(darkTheme = true, content = {
        HomeScreen(
            accountBalance = 1500f,
            investedBalance = 500f,
            onDepositClick = {},
            onWithdrawClick = {},
            onTransferClick = {},
            onInvestClick = {}
        )
    }
    )
}

@Composable
fun ActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp) // Ajusta el tamaño del ícono
            )
            Spacer(modifier = Modifier.height(4.dp)) // Espacio entre ícono y texto
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Composable
fun DonutChart(
    backgroundColor: Color,
    sliceColor: Color,
    slicePercentage: Float // Porcentaje del segmento (0.0f a 1.0f)
) {
    Canvas(
        modifier = Modifier
            .size(200.dp) // Tamaño de la dona
    ) {
        val size = size.minDimension // Tamaño del lienzo
        val strokeWidth = size * 0.1f // Grosor de la dona

        // Fondo de la dona
        drawCircle(
            color = backgroundColor,
            radius = size / 2 - strokeWidth / 2,
            style = Stroke(width = strokeWidth)
        )

        // Segmento coloreado
        drawArc(
            color = sliceColor,
            startAngle = -90f, // Comienza desde la parte superior
            sweepAngle = slicePercentage * 360f, // Ángulo del segmento
            useCenter = false,
            style = Stroke(width = strokeWidth),
            size = Size(size, size),
            topLeft = Offset((this.size.width - size) / 2, (this.size.height - size) / 2)
        )
    }
}


*/
