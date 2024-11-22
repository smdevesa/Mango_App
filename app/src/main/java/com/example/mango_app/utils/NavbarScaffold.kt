package com.example.mango_app.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavbarScaffold(
    navController: NavController,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            FootScreenBar(
                selectedRoute = currentRoute ?: "",
                onClickHome = { navController.navigate("home") },
                onClickHistory = { navController.navigate("history") },
                onClickCard = { navController.navigate("card") },
                onClickProfile = { navController.navigate("profile") }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}