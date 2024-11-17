package com.example.mango_app.ui.screen

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.mango_app.R
import com.example.mango_app.ui.theme.BackgroundColor
import com.example.mango_app.ui.theme.MangoOrange
import com.example.mango_app.utils.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onForgotPassword: () -> Unit, onRegisterClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = { TopAppBar(title = { Text("Login") }) },
        content = { padding ->
            Column(
                modifier = Modifier
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
                    label = "Mail",
                    leadingIcon = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Campo de Contraseña
                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Botón de Login
                Button(onClick = {
                    // Lógica de autenticación
                    onLoginSuccess() },
                    modifier = Modifier.height(60.dp).width(200.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MangoOrange)
                )
                {
                    Text(
                        text = "Iniciar sesión",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp), // Tamaño personalizado
                        color = Color.White // Color del texto
                    )
                }

                // Links para acciones adicionales
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Link para registro
                    Text(
                        text = "Don't have an account? Register",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                        modifier = Modifier.clickable {
                            onRegisterClick()
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    // Link para recuperar contraseña
                    Text(
                        text = "Forgot your password?",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                        modifier = Modifier.clickable {
                            onForgotPassword()
                        }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        onLoginSuccess = {},
        onForgotPassword = {},
        onRegisterClick = {}
    )
}
