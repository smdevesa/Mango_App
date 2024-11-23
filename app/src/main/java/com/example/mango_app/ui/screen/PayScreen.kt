package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mango_app.R
import com.example.mango_app.utils.CustomTextField
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.viewmodel.PayViewModel

@Composable
fun PayScreen(payViewModel: PayViewModel) {
    var paymentLink by remember { mutableStateOf("") }


    TitledCard(
        title = stringResource(id = R.string.pay),
        modifier = Modifier.fillMaxSize()
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween, // Espaciado entre elementos
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            CustomTextField(
                value = paymentLink,
                onValueChange = { paymentLink = it },
                label = stringResource(id = R.string.payment_link)
            
                )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    //val linkId =
                    //navController.navigate("paymentDetails/$linkId")
                },
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(text = stringResource(id = R.string.next))

            }



        }
    }
}