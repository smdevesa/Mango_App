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
//import com.example.mango_app.ui.screen.HomeScreen
import com.example.mango_app.ui.screen.HomeScreenTF
import com.example.mango_app.ui.screen.LoginScreen
import com.example.mango_app.ui.screen.RegisterScreen
import com.example.mango_app.ui.theme.Mango_AppTheme
import androidx.compose.foundation.isSystemInDarkTheme
import com.example.mango_app.model.ApiService
import com.example.mango_app.model.RetrofitServiceFactory
import com.example.mango_app.ui.screen.VerifyScreen
import com.example.mango_app.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService: ApiService = RetrofitServiceFactory.makeRetrofitService()

        setContent {
            AppContent(apiService)
        }
    }
}

@Composable
fun AppContent(apiService: ApiService) {
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
                        LoginViewModel(apiService),
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
                    HomeScreenTF(
                        20.0, // Balance
                        onClickHome = {
                            navController.navigate("home")
                        },
                        onClickHistory = {
                            navController.navigate("history")
                        },
                        onClickData = {
                            navController.navigate("data")
                        },
                        onClickProfile = {
                            navController.navigate("profile")
                        },
                        onTransferClick = {}, // Acción vacía temporalmente
                        onDepositClick = {},  // Acción vacía temporalmente
                        onInvestClick = {},   // Acción vacía temporalmente
                        onSeeMyDataClick = {}, // Acción vacía temporalmente
                        onViewAllTransactions = {} // Acción vacía temporalmente
                    )
                }
            }
        }
    }
}
