package com.example.mango_app.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.mango_app.utils.BalanceIndicator
import com.example.mango_app.utils.FootScreenBar
import com.example.mango_app.utils.ThinDivider
import com.example.mango_app.utils.TopScreenBarForHome
import com.example.mango_app.utils.TransactionItem
import com.example.mango_app.viewmodel.HomeViewModel
import java.util.Date

@Composable
fun HomeScreenTF(
    homeViewModel: HomeViewModel,
    onTransferClick: () -> Unit,
    onDepositClick: () -> Unit,
    onInvestClick: () -> Unit,
    onClickData:  () -> Unit,
) {
    val firstName: String by homeViewModel.firstName.observeAsState("")
    val lastName: String by homeViewModel.lastName.observeAsState("")
    val balance: Int by homeViewModel.balance.observeAsState(0)

    Scaffold(
        topBar = {
            TopScreenBarForHome(username = "$firstName $lastName")
        },
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
                BalanceIndicator(
                    balance = balance.toDouble()
                )

                ThinDivider()

                Box(
                    Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                        .padding(top = 10.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp),
                    ) {
                        // Acciones rápidas
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            listOf(
                                Triple(R.drawable.baseline_send_24, R.string.transfer, onTransferClick),
                                Triple(R.drawable.baseline_call_received_24, R.string.deposit, onDepositClick),
                                Triple(R.drawable.baseline_trending_up_24, R.string.invest, onInvestClick),
                                Triple(R.drawable.baseline_account_balance_wallet_24, R.string.data, onClickData)
                            ).forEach { (icon, textId, onClick) ->
                                ActionButton(
                                    icon = painterResource(id = icon),
                                    text = stringResource(id = textId),
                                    onClick = onClick,
                                    size = 60.dp,
                                    fontSize = 15.sp,
                                    modifier = Modifier.width(80.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        ThinDivider()

                        Text(
                            text = "Últimas transacciones",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre ítems
                        ) {
                            items(listOf(
                                Transaction(
                                    id = "1",
                                    description = "Pago en supermercado",
                                    amount = -50.25,
                                    date = Date()
                                ),
                                Transaction(
                                    id = "2",
                                    description = "Pago en cobayeria",
                                    amount = -20.00,
                                    date = Date()
                                ),
                                Transaction(
                                    id = "3",
                                    description = "Pago en devesa",
                                    amount = -35.00,
                                    date = Date()
                                )
                            )) { transaction ->
                                TransactionItem(transaction = transaction) {}
                            }
                        }

                    }
                }
            }
        }
    }
}
