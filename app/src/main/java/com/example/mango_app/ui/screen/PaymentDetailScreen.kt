package com.example.mango_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mango_app.R
import com.example.mango_app.utils.CardDesign
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.viewmodel.PayViewModel

@Composable
fun PaymentDetailScreen(
    linkUuid: String,
    navController: NavHostController,
    payViewModel: PayViewModel
) {
    val amount by payViewModel.amount.observeAsState("")
    val cards by payViewModel.cards.observeAsState(emptyList())
    val successMessageVisible by payViewModel.successMessageVisible.observeAsState(false)
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { cards.size })
    val showSuccessAlert = remember { mutableStateOf(false) }
    var showCardCarousel by remember { mutableStateOf(false) }
    var disableBalancePayment by remember { mutableStateOf(false) }
    var disableCardPayment by remember { mutableStateOf(false) }
    var showNoCardsMessage by remember { mutableStateOf(false) }
    var showBalanceDialog by remember { mutableStateOf(false) }
    val insufficientFunds by payViewModel.insufficientFunds.observeAsState(false)
    val showFailureAlert = remember { mutableStateOf(false) }



    LaunchedEffect(linkUuid) {
        payViewModel.fetchPaymentDetails(linkUuid)
    }

    LaunchedEffect(insufficientFunds) {
        if (insufficientFunds) {
            showFailureAlert.value = true
        }
    }


    LaunchedEffect(successMessageVisible) {
        if (successMessageVisible) {
            showSuccessAlert.value = true
        }
        if (insufficientFunds) {
            showFailureAlert.value = true
        }
    }


    TitledCard(
        title = "",
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text =  stringResource(id= R.string.toPay) + amount,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 24.dp)
                )

                Button(
                    onClick = {
                        showBalanceDialog = true
                              },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !disableBalancePayment
                ) {
                    Text(text = stringResource(id = R.string.pay_with_balance),
                        style = MaterialTheme.typography.titleSmall)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (cards.isEmpty()) {
                            showNoCardsMessage = true
                        } else {
                            showCardCarousel = true
                            disableBalancePayment = true
                            disableCardPayment = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !disableCardPayment
                ) {
                    Text(style = MaterialTheme.typography.titleSmall,
                        text = stringResource(id = R.string.pay_with_card))
                }
                if (cards.isEmpty()) {
                    if (showNoCardsMessage) {
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (showCardCarousel && cards.isNotEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .width(390.dp)
                                .padding(top = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
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
                                                    MaterialTheme.colorScheme.onBackground.copy(
                                                        alpha = 0.5f
                                                    )
                                                },
                                                shape = CircleShape
                                            )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.width(310.dp),
                            ) {
                                TextButton(
                                    onClick = {
                                        showCardCarousel = false
                                        disableBalancePayment = false
                                        disableCardPayment = false
                                    }
                                ) {
                                    Text(color = MaterialTheme.colorScheme.onError,
                                        style = MaterialTheme.typography.titleSmall,
                                        text = stringResource(id = R.string.cancel))

                                }

                                Button(
                                    onClick = {
                                        val selectedCardId = cards[pagerState.currentPage].id
                                        payViewModel.setCardId(selectedCardId)
                                        payViewModel.payWithCard(linkUuid, selectedCardId)
                                        showCardCarousel = false
                                        disableBalancePayment = false
                                        disableCardPayment = false
                                    }
                                ) {
                                    Text(style = MaterialTheme.typography.titleSmall,
                                        text = stringResource(id = R.string.confirm_card))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if(showBalanceDialog) {
        AlertDialog(
            onDismissRequest = {
                showBalanceDialog = false
            },
            title = { Text(text = stringResource(id = R.string.confirm_payment)) },
            text = { Text(
                text = stringResource(id = R.string.confirm_payment_balance),
                style = MaterialTheme.typography.titleSmall,)
                    },
            confirmButton = {
                TextButton(
                    onClick = {
                        payViewModel.payWithBalance(linkUuid)
                        showBalanceDialog = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.confirm_card),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showBalanceDialog = false
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onError
                        )
                }
            }
        )
    }

    if (showSuccessAlert.value) {
        AlertDialog(
            onDismissRequest = {
                showSuccessAlert.value = false
                payViewModel.resetSuccessMessage()
                navController.navigate("home")
            },
            title = { Text(text = stringResource(id = R.string.success)) },
            text = { Text(
                text = stringResource(id = R.string.successful_payment),
                style = MaterialTheme.typography.titleSmall,
            )
                   },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSuccessAlert.value = false
                        payViewModel.resetSuccessMessage()
                        navController.navigate("home")
                    }
                ) {
                    Text(
                        text = stringResource( id = R.string.close),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
        )
    }

    if (showFailureAlert.value) {
        AlertDialog(
            onDismissRequest = {
                showFailureAlert.value = false
                payViewModel.resetInsufficientFunds()
            },
            title = { Text(text = stringResource(id = R.string.error)) },
            text = { Text(text = stringResource(id = R.string.insufficient_funds)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showFailureAlert.value = false
                        payViewModel.resetInsufficientFunds()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.close),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onError
                        )
                }
            }
        )
    }


}
