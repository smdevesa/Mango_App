package com.example.mango_app.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun TopBarScaffold(
    navController: NavController,
    backRoute: String,
    title: String,
    content: @Composable () -> Unit
) {
    Scaffold (
        topBar = {
            TopScreenBar(
                title = title,
                onClick = { navController.navigate(backRoute) }
            )
        }
    )
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}