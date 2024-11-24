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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.mango_app.utils.DataPopUp
import com.example.mango_app.utils.FootScreenBar
import com.example.mango_app.utils.NavbarButton
import com.example.mango_app.utils.ThinDivider
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.utils.TopScreenBarForHome
import com.example.mango_app.utils.TransactionItem
import com.example.mango_app.viewmodel.HomeViewModel
import java.util.Date

@Composable
fun HomeScreenTF(
    homeViewModel: HomeViewModel,
    onPayClick: () -> Unit,
    onDepositClick: () -> Unit,
    onInvestClick: () -> Unit,
) {
    val firstName: String by homeViewModel.firstName.observeAsState("")
    val lastName: String by homeViewModel.lastName.observeAsState("")
    val balance: Int by homeViewModel.balance.observeAsState(0)
    val cvu: String by homeViewModel.cvu.observeAsState("")
    val alias: String by homeViewModel.alias.observeAsState("")
    val showDataPopUp = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopScreenBarForHome(username = "$firstName $lastName")
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 2.dp)
            ) {
                TitledCard(
                    stringResource(id = R.string.available_balance),
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        BalanceIndicator(balance.toDouble())
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            listOf(
                                Triple(
                                    R.drawable.baseline_attach_money_24,
                                    R.string.pay,
                                    onPayClick
                                ),
                                Triple(
                                    R.drawable.baseline_account_balance_24,
                                    R.string.deposit,
                                    onDepositClick
                                ),
                                Triple(
                                    R.drawable.baseline_trending_up_24,
                                    R.string.invest,
                                    onInvestClick
                                ),
                                Triple(
                                    R.drawable.baseline_account_balance_wallet_24,
                                    R.string.data,
                                ) {
                                    showDataPopUp.value = true
                                }
                            ).forEach { (icon, textId, onClick) ->
                                NavbarButton(
                                    icon = painterResource(id = icon),
                                    text = stringResource(id = textId),
                                    onClick = onClick,
                                    size = 60,
                                    fontSize = 16,
                                    iconColor = MaterialTheme.colorScheme.primary,
                                    textColor = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
                TitledCard(
                    title = stringResource(id = R.string.last_transactions),
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                    ) {
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
                            items(
                                listOf(
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
                                )
                            ) { transaction ->
                                TransactionItem(transaction = transaction) {}
                            }
                        }

                    }
                }
            }
        }
    }
    if (showDataPopUp.value) {
        DataPopUp(cvu, alias) {
            showDataPopUp.value = false
        }
    }
}
