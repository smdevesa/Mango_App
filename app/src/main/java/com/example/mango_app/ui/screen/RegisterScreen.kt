package com.example.mango_app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.example.mango_app.ui.theme.*
import com.example.mango_app.utils.CustomTextField
import com.example.mango_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit, onLoginClick: () -> Unit) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        containerColor = BackgroundColor,
        topBar = { TopAppBar(title = { Text("Register") }) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mango_logo),
                    contentDescription = "Logo de Mango",
                    modifier = Modifier
                        .size(250.dp) // Ajusta el tamaño de la imagen
                )

                // Full Name Field
                CustomTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = "Usuario",
                    leadingIcon = Icons.Default.Person
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Email Field
                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Mail",
                    leadingIcon = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Phone Number Field
                CustomTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = "Teléfono",
                    leadingIcon = Icons.Default.Phone,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Password Field
                CustomTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Repetir Contraseña",
                    leadingIcon = Icons.Default.Lock,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Login Link
                Text(
                    text = "Already have an account? Login",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                    modifier = Modifier.clickable { onLoginClick() }
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Register Button
                Button(onClick = { onRegisterSuccess() },
                    modifier = Modifier.height(60.dp).width(200.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MangoOrange)
                ) {
                    Text(
                        text = "Registrarse",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp), // Tamaño personalizado
                        color = Color.White // Color del texto
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(onRegisterSuccess = {}, onLoginClick = {})
}
