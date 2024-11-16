package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Bienvenido a MANGO$!")

        Text(text = "Saldo disponible: $100.00")

        Button(onClick = { navController.navigate("payment") }) {
            Text(text = "Ir a pagos")
        }

        Button(onClick = { navController.navigate("transactions") }) {
            Text(text = "Ver transacciones")
        }
    }
}
