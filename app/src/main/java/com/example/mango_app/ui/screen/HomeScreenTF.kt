package com.example.mango_app.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.model.Transaction
import com.example.mango_app.utils.BalanceIndicator
import com.example.mango_app.utils.DataPopUp
import com.example.mango_app.utils.NavbarButton
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
    val showDataPopUp = rememberSaveable { mutableStateOf(false) }
    val listState = rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopScreenBarForHome(username = "$firstName $lastName")
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = rememberLazyListState(initialFirstVisibleItemIndex = listState.intValue),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
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
                }

                item {
                    TitledCard(
                        title = stringResource(id = R.string.last_transactions),
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Últimas transacciones",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(max = 300.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(
                                    listOf(
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
                                ) { transaction ->
                                    TransactionItem(transaction = transaction) {}
                                }
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
