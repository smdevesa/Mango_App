package com.example.mango_app.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.model.RetrofitServiceFactory
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.CustomTextField
import com.example.mango_app.viewmodel.VerifyViewModel

@Composable
fun VerifyScreen(verifyViewModel: VerifyViewModel, onVerifySuccess: () -> Unit) {

    val scrollState = rememberScrollState()
    val hasNavigated = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        )
    {

    }
}

@Composable
fun VerificationCodeTextField(verificationCode: String, onValueChange: (String) -> Unit) {
    CustomTextField(
        value = "",
        onValueChange = { onValueChange(it) },
        label = stringResource(id = R.string.verification_code_hint),
        leadingIcon = Icons.Default.Phone,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
    )
}

@Preview
@Composable
fun VerifyScreenPreviewLight() {
    Mango_AppTheme(false) {
        VerifyScreen(VerifyViewModel(RetrofitServiceFactory.makeRetrofitService()), {})
    }
}

@Preview
@Composable
fun VerifyScreenPreviewDark() {
    Mango_AppTheme(true) {
        VerifyScreen(VerifyViewModel(RetrofitServiceFactory.makeRetrofitService()), {})
    }
}