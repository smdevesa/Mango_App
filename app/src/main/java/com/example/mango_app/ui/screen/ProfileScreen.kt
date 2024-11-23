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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mango_app.R
import com.example.mango_app.model.RetrofitServiceFactory
import com.example.mango_app.model.UserDataStore
import com.example.mango_app.utils.TitledCard
import com.example.mango_app.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, onLogoutSuccess: () -> Unit) {
    val fullName by profileViewModel.fullName.observeAsState("")
    val email by profileViewModel.email.observeAsState("")
    val startDate by profileViewModel.birthDate.observeAsState("")
    val event by profileViewModel.event.observeAsState(ProfileEvent.None)
    val hasNavigated = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        profileViewModel.loadProfile()
    }


    val profileItems = listOf(
        UserProfileItem(icon = R.drawable.baseline_account_circle_24, label = stringResource(id = R.string.full_name_hint), value = fullName),
        UserProfileItem(icon = R.drawable.baseline_email_24, label = stringResource(id = R.string.email_hint), value = email),
        UserProfileItem(icon = R.drawable.baseline_calendar_month_24, label = stringResource(id = R.string.created_at), value = startDate)
    )

    TitledCard(
        title = stringResource(id = R.string.profile),
        modifier = Modifier.fillMaxSize()
    ) {


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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* TODO: Add change password functionality */ },
                    //modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Cambiar Contraseña")
                }
                Button(
                    onClick = { profileViewModel.onLogoutClick() },
                    //modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.logout))
                }
            }
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
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 16.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
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




