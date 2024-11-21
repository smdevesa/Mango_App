package com.example.mango_app.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.model.Transaction
import com.example.mango_app.model.UserDataStore
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.ActionButton
import com.example.mango_app.utils.FootScreenBar
import com.example.mango_app.utils.TopScreenBarForHome
import com.example.mango_app.utils.TransactionItem
import com.example.mango_app.viewmodel.HomeViewModel
import java.util.Date

@Composable
fun HomeScreenTF(
    balance: Double,
    homeViewModel: HomeViewModel,
    userDataStore: UserDataStore,
    onTransferClick: () -> Unit,
    onDepositClick: () -> Unit,
    onInvestClick: () -> Unit,
    onClickData:  () -> Unit,
    onViewAllTransactions: () -> Unit,
    // Parámetros para la NavigationBar DESPUES DEL CAMBIO EN FOOTSCREENBAR NO VAN A SER NECESARIOS
    onClickHome: () -> Unit,
    onClickHistory: () -> Unit,
    onClickCard: () -> Unit,
    onClickProfile: () -> Unit
) {
    val firstName: String by homeViewModel.firstName.observeAsState("")
    val lastName: String by homeViewModel.lastName.observeAsState("")
    Scaffold(
        topBar = {
            TopScreenBarForHome(username = "$firstName $lastName")
        },
        bottomBar = {
            FootScreenBar(
                onClickHome,
                onClickHistory,
                onClickCard,
                onClickProfile
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 2.dp)
            ) {
                Box(){

                }
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
                            text = stringResource(id = R.string.available_balance),
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
                        .fillMaxSize()
                        .padding(top = 20.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                    ) {
                        // Acciones rápidas
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                             ActionButton(
                                icon = painterResource(id = R.drawable.baseline_send_24),
                                text = stringResource(id = R.string.transfer),
                                onClick = onTransferClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background,
                                size = 85.dp,
                                fontSize = 15.sp
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_call_received_24),
                                text = stringResource(id = R.string.deposit),
                                onClick = onDepositClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background,
                                size = 85.dp,
                                fontSize = 15.sp
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_trending_up_24),
                                text = stringResource(id = R.string.invest),
                                onClick = onInvestClick,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background,
                                size = 85.dp,
                                fontSize = 15.sp
                            )
                            ActionButton(
                                icon = painterResource(id = R.drawable.baseline_account_balance_wallet_24),
                                text = stringResource(id = R.string.data),
                                onClick = onClickData,
                                color = MaterialTheme.colorScheme.onBackground,
                                backgroundColor = MaterialTheme.colorScheme.background,
                                size = 85.dp,
                                fontSize = 15.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(
                            text = "Ultimas transacciones",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Column (
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        )

                        {
                            TransactionItem(
                                Transaction(
                                    id = "1",
                                    description = "Pago en supermercado",
                                    amount = -50.25,
                                    date = Date()
                                )
                            ) { }
                            Spacer(modifier = Modifier.height(8.dp))
                            TransactionItem(
                                Transaction(
                                    id = "1",
                                    description = "Pago en cobayeria",
                                    amount = -50.25,
                                    date = Date()
                                )
                            ){}
                            Spacer(modifier = Modifier.height(8.dp))
                            TransactionItem(
                                Transaction(
                                    id = "1",
                                    description = "Pago en devesa",
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


//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreviewDarkTF() {
//    Mango_AppTheme(darkTheme = true, content = {
//        HomeScreenTF(
//            balance = 1250.75,
//            onTransferClick = {},
//            onDepositClick = {},
//            onInvestClick = {},
//            onClickData = {},
//            onViewAllTransactions = {},
//            // Parámetros para la NavigationBar
//            onClickHome = {},
//            onClickHistory = {},
//            onClickCard = {},
//            onClickProfile = {}
//        )
//    })
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreviewLightTF() {
//    Mango_AppTheme(darkTheme = false, content = {
//        HomeScreenTF(
//            balance = 1250.75,
//            onTransferClick = {},
//            onDepositClick = {},
//            onInvestClick = {},
//            onClickData = {},
//            onViewAllTransactions = {},
//            // Parámetros para la NavigationBar
//            onClickHome = {},
//            onClickHistory = {},
//            onClickCard = {},
//            onClickProfile = {}
//        )
//    })
//}