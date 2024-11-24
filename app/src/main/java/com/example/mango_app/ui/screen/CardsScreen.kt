package com.example.mango_app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.model.data.Card
import com.example.mango_app.utils.ButtonMango
import com.example.mango_app.utils.CardDesign
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.viewmodel.CardViewModel

@Composable
fun CardsScreen(
    cardViewModel: CardViewModel,
) {
    val cards by cardViewModel.cards.observeAsState(emptyList())
    val isLoading by cardViewModel.isLoading.observeAsState(false)
    var showDialog by remember { mutableStateOf(false) }
    var cardToDelete by remember { mutableStateOf<Card?>(null) }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { cards.size })

    TitledCard(
        title = stringResource(id = R.string.cards_title),
        modifier = Modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(48.dp))
                }
            } else {
                if(cards.isEmpty()){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                if (cards.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.width(310.dp).wrapContentHeight()
                        ) { page ->
                            CardDesign(card = cards[page])
                        }
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
                    Row(
                        modifier = Modifier
                            .width(310.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(
                            onClick = {
                                cardToDelete = if (cards.isNotEmpty()) cards[pagerState.currentPage] else null
                                showDialog = true
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.delete_card),
                                color = MaterialTheme.colorScheme.onError,
                                style = MaterialTheme.typography.titleMedium
                            )

                        }
                        ButtonMango(
                            text = stringResource(id = R.string.add_card),
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.no_cards),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                        ButtonMango(
                            text = stringResource(id = R.string.add_card),
                        )

                }
            }

        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = stringResource(id = R.string.delete_card)) },
            text = { Text(
                text = stringResource(id = R.string.delete_card_message),
                style = MaterialTheme.typography.titleSmall
            ) },
            confirmButton = {
                TextButton(onClick = {
                    cardToDelete?.let { cardViewModel.deleteCard(it.id) }
                    showDialog = false
                }) {
                    Text(
                        text = stringResource(id = R.string.delete),
                        style = MaterialTheme.typography.titleSmall,
                        )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onError,
                    )
                }
            }
        )
    }
}
