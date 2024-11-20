package com.example.mango_app.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mango_app.R

@Composable
fun MangoLogo() {
    Image(
        painter = painterResource(id = R.drawable.mango_logo),
        contentDescription = "Logo de Mango",
        modifier = Modifier
            .size(250.dp)
    )
}