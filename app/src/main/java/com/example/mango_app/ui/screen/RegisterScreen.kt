package com.example.mango_app.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mango_app.utils.MangoLogo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.example.mango_app.ui.theme.*
import com.example.mango_app.utils.CustomTextField
import com.example.mango_app.R
import com.example.mango_app.model.RetrofitServiceFactory
import com.example.mango_app.viewmodel.RegisterViewModel

sealed class RegisterEvent {
    data class Error(val message: String) : RegisterEvent()
    data object RegisterSuccess : RegisterEvent()
    data object Loading : RegisterEvent()
    data object None : RegisterEvent()
}

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, onLoginClick: () -> Unit, onRegisterSuccess: () -> Unit) {

    val scrollState = rememberScrollState()

    val fullName: String by registerViewModel.fullName.observeAsState("")
    val email: String by registerViewModel.email.observeAsState("")
    val phone: String by registerViewModel.phone.observeAsState("")
    val password: String by registerViewModel.password.observeAsState("")
    val repeatPassword: String by registerViewModel.repeatPassword.observeAsState("")
    val registerEnable: Boolean by registerViewModel.registerEnable.observeAsState(false)
    val event: RegisterEvent by registerViewModel.event.observeAsState(RegisterEvent.None)

    val hasNavigated = remember { mutableStateOf(false) }

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
            ) {
                MangoLogo()
                FullNameTextField(fullName) {
                    registerViewModel.onRegisterChanged(
                        it,
                        email,
                        phone,
                        password,
                        repeatPassword
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                EmailTextField(email) {
                    registerViewModel.onRegisterChanged(
                        fullName,
                        it,
                        phone,
                        password,
                        repeatPassword
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PhoneTextField(phone) {
                    registerViewModel.onRegisterChanged(
                        fullName,
                        email,
                        it,
                        password,
                        repeatPassword
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PasswordTextField(password) {
                    registerViewModel.onRegisterChanged(
                        fullName,
                        email,
                        phone,
                        it,
                        repeatPassword
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                RepeatPasswordTextField(repeatPassword) {
                    registerViewModel.onRegisterChanged(
                        fullName,
                        email,
                        phone,
                        password,
                        it
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                if(event is RegisterEvent.Error) {
                    ErrorMessage((event as RegisterEvent.Error).message)
                }
                RegisterButton(registerEnable, event is RegisterEvent.Loading) {
                    registerViewModel.onRegisterClick()
                }

                Spacer(modifier = Modifier.height(8.dp))

                GoToLoginText {
                    onLoginClick()
                }
            }
        }
    )

    when(event) {
        is RegisterEvent.RegisterSuccess -> {
            if(!hasNavigated.value) {
                hasNavigated.value = true
                onRegisterSuccess()
            }
        }
        else -> {}
    }
}

@Composable
fun FullNameTextField(fullName: String = "", onValueChange: (String) -> Unit) {
    CustomTextField(
        value = fullName,
        onValueChange = { onValueChange(it) },
        label = stringResource(id = R.string.full_name_hint),
        leadingIcon = Icons.Default.Person
    )
}

@Composable
fun EmailTextField(email: String = "", onValueChange: (String) -> Unit) {
    CustomTextField(
        value = email,
        onValueChange = { onValueChange(it) },
        label = stringResource(id = R.string.email_hint),
        leadingIcon = Icons.Default.Email,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordTextField(password: String = "", onValueChange: (String) -> Unit) {
    CustomTextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = stringResource(id = R.string.password_hint),
        leadingIcon = Icons.Default.Lock,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun RepeatPasswordTextField(password: String = "", onValueChange: (String) -> Unit) {
    CustomTextField(
        value = password,
        onValueChange = { onValueChange(it) },
        label = stringResource(id = R.string.repeat_password_hint),
        leadingIcon = Icons.Default.Lock,
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun PhoneTextField(phoneNumber: String = "", onValueChange: (String) -> Unit) {
    CustomTextField(
        value = phoneNumber,
        onValueChange = { onValueChange(it) },
        label = stringResource(id = R.string.phone_number_hint),
        leadingIcon = Icons.Default.Phone,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
    )
}

@Composable
fun RegisterButton(registerEnable: Boolean, loading: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = registerEnable && !loading,
        modifier = Modifier.height(60.dp).width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        if(loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Text(
                text = stringResource(id = R.string.register_button),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

}

@Composable
fun GoToLoginText(onClick: () -> Unit) {
    Text(
        text = stringResource(id = R.string.login_message),
        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier.clickable { onClick() }
    )
}

@Composable
fun ErrorMessage(message: String) {
    Text(
        text = message,
        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.error)
    )
}
