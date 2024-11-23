package com.example.mango_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, onLogoutSuccess: () -> Unit) {
    val fullName by profileViewModel.fullName.observeAsState("")
    val email by profileViewModel.email.observeAsState("")
    val startDate by profileViewModel.startDate.observeAsState("")
    val event by profileViewModel.event.observeAsState(ProfileEvent.None)
    val hasNavigated = remember { mutableStateOf(false) }

    val profileItems = listOf(
        UserProfileItem(icon = R.drawable.baseline_account_circle_24, label = "Nombre", value = fullName),
        UserProfileItem(icon = R.drawable.baseline_email_24, label = "Email", value = email),
        UserProfileItem(icon = R.drawable.baseline_calendar_month_24, label = "Fecha de registro", value = startDate)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween, // Espaciado entre elementos
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Mostrar los datos del perfil
        profileItems.forEach { item ->
            ProfileItem(
                icon = item.icon,
                label = item.label,
                value = item.value
            )
        }

        // Espaciador para llevar el botón al fondo
        Spacer(modifier = Modifier.weight(1f))

        // Botón de cerrar sesión
        Button(
            onClick = { profileViewModel.onLogoutClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Cerrar Sesión")
        }
    }

    when (event) {
        is ProfileEvent.Loading -> {
            CircularProgressIndicator()
        }
        is ProfileEvent.Logout -> {
            if(!hasNavigated.value) {
                hasNavigated.value = true
                onLogoutSuccess()
            }
        }
        is ProfileEvent.None -> {
        }
    }
}

sealed class ProfileEvent {
    data object Loading : ProfileEvent()
    data object Logout : ProfileEvent()
    data object None: ProfileEvent()
}
data class UserProfileItem(
    val icon: Int, // Referencia al recurso del ícono
    val label: String,
    val value: String
)

@Composable
fun ProfileItem(
    icon: Int,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 8.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

