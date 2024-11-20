package com.example.mango_app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.RetrofitServiceFactory
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.MangoLogo
import com.example.mango_app.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, onRegisterClick : () -> Unit, onForgotPasswordClick : () -> Unit, onLoginSuccess : () -> Unit) {

    val email by loginViewModel.email.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    val loginEnable by loginViewModel.loginEnable.observeAsState(false)
    val event by loginViewModel.event.observeAsState(LoginEvent.None)

    val hasNavigated = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        content = { padding ->
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(padding)
                    .fillMaxSize()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )

            {
                Spacer(modifier = Modifier.height(32.dp))

                MangoLogo()
                EmailTextField(email) {
                    loginViewModel.onLoginChanged(it, password)
                }
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(password) {
                    loginViewModel.onLoginChanged(email, it)
                }
                Row(
                    modifier = Modifier.align(Alignment.End)
                ) {
                    ForgotPasswordText {
                        onForgotPasswordClick()
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                if(event is LoginEvent.Error) {
                    Text(
                        text = (event as LoginEvent.Error).message,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
                    )
                }
                LoginButton(loginEnable) {
                    loginViewModel.onLoginClick()
                }
                Spacer(modifier = Modifier.height(16.dp))
                GoToRegisterText { onRegisterClick() }

            }
        }
    )

    if(event is LoginEvent.Success && !hasNavigated.value) {
        hasNavigated.value = true
        onLoginSuccess()
    }
}

@Composable
fun ForgotPasswordText(onClick: () -> Unit) {
    Text(
        text = stringResource(id = R.string.forgot_password),
        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
        modifier = Modifier.clickable { onClick() }
    )
}

@Composable
fun LoginButton(enable: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enable,
        modifier = Modifier.height(60.dp).width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = stringResource(id = R.string.login_button),
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

sealed class LoginEvent {
    data class Error(val message: String) : LoginEvent()
    data object Success : LoginEvent()
    data object Loading : LoginEvent()
    data object None : LoginEvent()
}

@Composable
fun GoToRegisterText(onClick: () -> Unit) {
    Text(
        text = stringResource(id = R.string.register_message),
        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
        modifier = Modifier.clickable { onClick() }
    )
}
