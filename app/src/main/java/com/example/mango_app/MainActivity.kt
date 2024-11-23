package com.example.mango_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mango_app.ui.screen.HomeScreenTF
import com.example.mango_app.ui.screen.LoginScreen
import com.example.mango_app.ui.screen.RegisterScreen
import com.example.mango_app.ui.theme.Mango_AppTheme
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.RetrofitServiceFactory
import com.example.mango_app.model.UserDataStore
import com.example.mango_app.ui.screen.AddMoneyScreen
import com.example.mango_app.ui.screen.CardsScreen
import com.example.mango_app.ui.screen.ProfileScreen
import com.example.mango_app.ui.screen.VerifyScreen
import com.example.mango_app.utils.NavbarScaffold
import com.example.mango_app.viewmodel.*
import com.example.mango_app.ui.screen.TransactionHistoryScreen
import com.example.mango_app.ui.screen.fakeTransactions
import com.example.mango_app.utils.TopBarScaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userDataStore = UserDataStore(this)
        val apiService: ApiService = RetrofitServiceFactory.makeRetrofitService(userDataStore)

        setContent {
            AppContent(apiService, userDataStore)
        }
    }
}

@Composable
fun AppContent(apiService: ApiService, userDataStore: UserDataStore) {
    val systemDarkMode = isSystemInDarkTheme()

    val darkModeState = remember { mutableStateOf(systemDarkMode) }

    LaunchedEffect(systemDarkMode) {
        darkModeState.value = systemDarkMode
    }

    Mango_AppTheme(darkTheme = darkModeState.value) {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = "login",
                modifier = Modifier.padding(padding)
            ) {
                composable("login") {
                    LoginScreen(
                        LoginViewModel(apiService, userDataStore),
                        onRegisterClick = {
                            navController.navigate("register")
                        },
                        onForgotPasswordClick = {
                            // TODO: Ver si se implementa esta funcionalidad
                        },
                        onLoginSuccess = {
                            navController.navigate("home")
                        }
                    )
                }
                composable("register") {
                    RegisterScreen(
                        RegisterViewModel(apiService),
                        onLoginClick = {
                            navController.navigate("login")
                        },
                        onRegisterSuccess = {
                            navController.navigate("verify")
                        }
                    )
                }
                composable("verify") {
                    VerifyScreen(
                        VerifyViewModel(apiService),
                        onVerifySuccess = {
                            navController.navigate("login")
                        }
                    )
                }
                composable("home") {
                    NavbarScaffold(navController) {
                        HomeScreenTF(
                            HomeViewModel(apiService, userDataStore),
                            onTransferClick = {}, // Acción vacía temporalmente
                            onDepositClick = { navController.navigate("add-money") },
                            onInvestClick = {},   // Acción vacía temporalmente
                        )
                    }
                }
                composable("card"){
                    NavbarScaffold(navController) {
                        CardsScreen(CardViewModel(apiService))
                    }
                }
                composable("history") {
                    NavbarScaffold(navController) {
                        TransactionHistoryScreen(fakeTransactions(), {})
                    }
                }
                composable("profile"){
                    NavbarScaffold(navController) {
                        ProfileScreen(ProfileViewModel(apiService)) {
                            navController.navigate("login")
                        }
                    }
                }
                composable("add-money") {
                    TopBarScaffold(
                        navController,
                        backRoute = "home",
                        title = "Agregar dinero",
                    ) {
                        AddMoneyScreen(AddMoneyViewModel(apiService))
                    }
                }
            }
        }
    }
}
