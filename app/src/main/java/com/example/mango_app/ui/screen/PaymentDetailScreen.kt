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
import androidx.compose.ui.unit.sp
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
    var showNoCardsMessage by remember { mutableStateOf(false) }  // Flag to show message if no cards are available

    // Cargar detalles del pago al iniciar la pantalla
    LaunchedEffect(linkUuid) {
        payViewModel.fetchPaymentDetails(linkUuid)
    }

    // Mostrar alerta de éxito si el pago fue exitoso
    LaunchedEffect(successMessageVisible) {
        if (successMessageVisible) {
            showSuccessAlert.value = true
        }
    }

    TitledCard(
        title = "",
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Habilitar scroll vertical
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Mostrar monto a pagar y botones
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text =  stringResource(id= R.string.toPay) + amount,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 24.dp)
                )

                // Botón para pagar con saldo
                Button(
                    onClick = { payViewModel.payWithBalance(linkUuid) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !disableBalancePayment
                ) {
                    Text(text = stringResource(id = R.string.pay_with_balance),
                        style = MaterialTheme.typography.titleSmall)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para abrir el carrusel de tarjetas
                Button(
                    onClick = {
                        if (cards.isEmpty()) {
                            showNoCardsMessage = true // Muestra el mensaje de no hay tarjetas
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
                // Verifica si hay tarjetas y muestra el mensaje si no las hay
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
                    // Mostrar carrusel de tarjetas
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
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

                            // Indicador de página
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

                            // Confirmar tarjeta seleccionada y opción de cancelar
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
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

    // Mostrar alerta de éxito al completar el pago
    if (showSuccessAlert.value) {
        AlertDialog(
            onDismissRequest = {
                showSuccessAlert.value = false
                payViewModel.resetSuccessMessage()
                navController.navigate("home")
            },
            title = { Text(text = stringResource(id = R.string.success)) },
            text = { Text(text = stringResource(id = R.string.successful_payment)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSuccessAlert.value = false
                        payViewModel.resetSuccessMessage()
                        navController.navigate("home")
                    }
                ) {
                    Text(text = stringResource( id = R.string.close) )
                }
            }
        )
    }
}
