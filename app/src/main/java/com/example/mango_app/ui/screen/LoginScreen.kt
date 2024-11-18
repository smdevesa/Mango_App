package com.example.mango_app.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.ui.theme.Mango_AppTheme
import com.example.mango_app.utils.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onForgotPassword: () -> Unit, onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
                Image(
                    painter = painterResource(id = R.drawable.mango_logo),
                    contentDescription = "Logo de Mango",
                    modifier = Modifier
                        .size(250.dp) // Ajusta el tamaño de la imagen
                )
                
                // Campo de Email
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = stringResource(id = R.string.email_hint),
                    leadingIcon = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Campo de Contraseña
                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.password_hint),
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation()
                )
                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.clickable {
                        onForgotPassword()
                    }.align(Alignment.End)
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Botón de Login
                Button(onClick = {
                    // Lógica de autenticación
                    onLoginSuccess() },
                    modifier = Modifier.height(60.dp).width(200.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                )
                {
                    Text(
                        text = stringResource(id = R.string.login_button),
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.register_message),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.clickable {
                        onRegisterClick()
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreviewLight() {
    Mango_AppTheme(darkTheme = false, content = {
        LoginScreen(onLoginSuccess = {}, onForgotPassword = {}, onRegisterClick = {})
    })
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreviewDark() {
    Mango_AppTheme(darkTheme = true, content = {
        LoginScreen(onLoginSuccess = {}, onForgotPassword = {}, onRegisterClick = {})
    })
}
