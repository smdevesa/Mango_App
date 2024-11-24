package com.example.mango_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mango_app.R
import com.example.mango_app.utils.CardDesign
import com.example.mango_app.utils.CustomTextField
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.viewmodel.AddMoneyViewModel

@Composable
fun AddMoneyScreen(addMoneyViewModel: AddMoneyViewModel, navController: NavHostController) {
    val cards by addMoneyViewModel.cards.observeAsState(emptyList())
    val amount by addMoneyViewModel.amount.observeAsState("")
    val successMessageVisible by addMoneyViewModel.successMessageVisible.observeAsState(false)
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { cards.size })

    var showDialog by remember { mutableStateOf(false) }
    var selectedCardId by remember { mutableStateOf<Int?>(null) }

    TitledCard(
        title = "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (cards.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.no_cards),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .wrapContentHeight()
                            .width(310.dp)
                            .padding(horizontal = 32.dp)
                    ) { page ->
                        CardDesign(card = cards[page])
                    }

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(cards.size) { index ->
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(horizontal = 6.dp)
                                    .background(
                                        color = if (index == pagerState.currentPage) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                                        },
                                        shape = CircleShape
                                    )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    CustomTextField(
                        value = amount,
                        onValueChange = { addMoneyViewModel.setAmount(it) },
                        label =  stringResource(id = R.string.enter_amount),
                        filterDigitsOnly = true,
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            selectedCardId = cards[pagerState.currentPage].id
                            if (amount.isNotEmpty()) {
                                showDialog = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.add_money),
                            style = MaterialTheme.typography.titleSmall)
                    }
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    selectedCardId?.let { addMoneyViewModel.setCardId(it) }
                    addMoneyViewModel.addMoney()
                    showDialog = false
                }) {
                    Text(
                        text = stringResource(id = R.string.confirm_card),
                            style = MaterialTheme.typography.titleSmall
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            },
            title = { Text(text = stringResource(id = R.string.add_money)) },
            text = { Text(
                text = stringResource(id = R.string.add_money_confirmation),
                style = MaterialTheme.typography.titleSmall)
                   },
        )
    }

    if (successMessageVisible) {
        AlertDialog(
            onDismissRequest = { addMoneyViewModel.dismissSuccessMessage()
                navController.navigate("home")
            },
            confirmButton = {
                TextButton(onClick = { addMoneyViewModel.dismissSuccessMessage()
                    navController.navigate("home")
                }) {
                    Text(
                        text = stringResource(id = R.string.close),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onError
                    )

                }

            },
            title = { Text(text = stringResource(id = R.string.success)) },
            text = { Text(
                text = stringResource(id = R.string.money_success),
                style = MaterialTheme.typography.titleSmall)
                   },
        )
    }
}
