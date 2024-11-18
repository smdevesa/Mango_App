package com.example.mango_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mango_app.ui.screen.HomeScreen
import com.example.mango_app.ui.screen.LoginScreen
import com.example.mango_app.ui.screen.RegisterScreen
import com.example.mango_app.ui.theme.Mango_AppTheme
import androidx.compose.foundation.isSystemInDarkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    val systemDarkMode = isSystemInDarkTheme()

    val darkModeState = remember { mutableStateOf(systemDarkMode) }

    LaunchedEffect(systemDarkMode) {
        darkModeState.value = systemDarkMode
    }

    Mango_AppTheme(darkTheme = darkModeState.value, content = {
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
                        onLoginSuccess = {
                            navController.navigate("home")
                        },
                        onForgotPassword = {
                            // Aquí iría la lógica para recuperar contraseña
                        },
                        onRegisterClick = {
                            navController.navigate("register")
                        }
                    )
                }
                composable("register") {
                    RegisterScreen(
                        onRegisterSuccess = {
                            navController.navigate("home")
                        },
                        onLoginClick = {
                            navController.navigate("login")
                        }
                    )
                }
                composable("home") {
                    HomeScreen(navController)
                }
            }
        }
    }
    )
}
