package com.example.mango_app.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.model.RetrofitServiceFactory
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.CustomTextField
import com.example.mango_app.viewmodel.VerifyViewModel

@Composable
fun VerifyScreen(verifyViewModel: VerifyViewModel, onVerifySuccess: () -> Unit) {

    val scrollState = rememberScrollState()
    val hasNavigated = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val verificationCode: String by verifyViewModel.verificationCode.observeAsState("")
    val verifyEnable: Boolean by verifyViewModel.verifyEnable.observeAsState(false)
    val event: VerifyEvent by verifyViewModel.event.observeAsState(VerifyEvent.None)

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
        Text(
            text = stringResource(id = R.string.verify_account_title),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.verify_account_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        VerificationCodeTextField(verificationCode, verifyViewModel::onVerifyChanged)
        Spacer(modifier = Modifier.height(16.dp))
        if(event is VerifyEvent.Error) {
            Text(
                text = (event as VerifyEvent.Error).message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        VerifyButton(verifyEnable, event is VerifyEvent.Loading, verifyViewModel::onVerifyClick)
    }
     if(event is VerifyEvent.VerifySuccess && !hasNavigated.value) {
        showDialog.value = true
     }

    if(showDialog.value) {
        SuccessDialog(onDismiss = {
            showDialog.value = false
            hasNavigated.value = true
            onVerifySuccess()
        })
    }
}

sealed class VerifyEvent {
    data class Error(val message: String) : VerifyEvent()
    data object VerifySuccess : VerifyEvent()
    data object Loading : VerifyEvent()
    data object None : VerifyEvent()
}

@Composable
fun SuccessDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.complete_verification),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.complete_verification_message),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
fun VerificationCodeTextField(verificationCode: String, onValueChange: (String) -> Unit) {
    CustomTextField(
        value = verificationCode,
        onValueChange = onValueChange,
        label = stringResource(id = R.string.verification_code_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Unspecified)
    )
}

@Composable
fun VerifyButton(verifyEnable: Boolean, loading: Boolean, onVerifyClick: () -> Unit) {
    Button(
        onClick = onVerifyClick,
        enabled = verifyEnable && !loading,
        modifier = Modifier
            .height(60.dp)
            .width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        if(loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Text(
                text = stringResource(id = R.string.verify_account),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun SuccessDialogPreview() {
    Mango_AppTheme(false) {
        SuccessDialog {}
    }
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