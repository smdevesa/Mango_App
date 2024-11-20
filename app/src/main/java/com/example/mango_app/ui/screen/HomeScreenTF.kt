package com.example.mango_app.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.model.Transaction
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.FootScreenBar
import com.example.mango_app.utils.TransactionItem
import java.util.Date


@Composable
fun ActionButton(
    icon: Painter,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary, // Nuevo parámetro para el fondo
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(75.dp) // Tamaño del contenedor
            .background(color = backgroundColor, shape = CircleShape) // Fondo con forma
            .border(width = 1.5.dp, color = MaterialTheme.colorScheme.secondary, shape = CircleShape) // Borde negro
            .clickable(onClick = onClick)
            .padding(1.dp), // Padding para el contenedor completo
        contentAlignment = Alignment.Center
    ) {
        Column(
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
}



/*@Composable
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DonutChart(
                        backgroundColor = Color.Blue,
                        sliceColor = Color.Blue,
                        slicePercentage = 1f // 100% del círculo
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Saldo disponible",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "$ $balance",
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.tertiary)
                        //.fillMaxWidth()
                        .fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 30.dp),
                    ) {
                        // Acciones rápidas
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                                text = stringResource(id = R.string.transfer),
                                onClick = onTransferClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                                text = stringResource(id = R.string.deposit),
                                onClick = onDepositClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                                text = stringResource(id = R.string.invest),
                                onClick = onInvestClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                                text = stringResource(id = R.string.data),
                                onClick = onSeeMyDataClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background
                            )


                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Ultimas transacciones",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Column {
                            TransactionItem(
                                Transaction(
                                    id = "1",
                                    description = "Pago en Supermercado",
                                    amount = -50.25,
                                    date = Date()
                                )
                            ) { }
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
}*/

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
    Scaffold(
        bottomBar = {
            FootScreenBar(
                onClickHome,
                onClickHistory,
                onClickData,
                onClickProfile
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DonutChart(
                        backgroundColor = Color.Blue,
                        sliceColor = Color.Blue,
                        slicePercentage = 1f // 100% del círculo
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Saldo disponible",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "$ $balance",
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.tertiary)
                        .fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 30.dp),
                    ) {
                        // Acciones rápidas
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                                text = stringResource(id = R.string.transfer),
                                onClick = onTransferClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                                text = stringResource(id = R.string.deposit),
                                onClick = onDepositClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                                text = stringResource(id = R.string.invest),
                                onClick = onInvestClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24),
                                text = stringResource(id = R.string.data),
                                onClick = onSeeMyDataClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Ultimas transacciones",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Column (
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        )

                        {
                            TransactionItem(
                                Transaction(
                                    id = "1",
                                    description = "Pago en Supermercado",
                                    amount = -50.25,
                                    date = Date()
                                )
                            ) { }
                            TransactionItem(
                                Transaction(
                                    id = "1",
                                    description = "Pago en Cobayeria",
                                    amount = -50.25,
                                    date = Date()
                                )
                            ){}
                            TransactionItem(
                                Transaction(
                                    id = "1",
                                    description = "Pago en Devesa",
                                    amount = -50.25,
                                    date = Date()
                                )
                            ){}
                        }
                    }
                }
            }
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

