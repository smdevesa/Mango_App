package com.example.mango_app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = MangoOrange, // Mantienes el mismo naranja, ya que es un color llamativo
    secondary = Color(0xFF121212), // Color secundario oscuro
    tertiary = MangoGreen, // Verde suave que se puede usar en detalles
    background = Color(0xFF1A1C1B), // Fondo oscuro clásico
    onPrimary = Color.Black, // Texto claro sobre colores primarios
    onSurface = Color.LightGray, // Texto claro sobre fondo oscuro
    onBackground = Color(0xFF333333), // Fondo más oscuro para contenedores
    onSecondary = Color.White, // Texto claro sobre colores secundarios
    surface = Color(0xFF121212)
)

private val LightColorScheme = lightColorScheme(
    primary = MangoOrange,
    secondary = MangoGray,
    tertiary = MangoGreen,
    background = LightBackgroundColor,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = MangoGray,
    surface = Color(0xFFF5F5F5)
)

@Composable
fun Mango_AppTheme(content: @Composable () -> Unit, darkTheme: Boolean = isSystemInDarkTheme()) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
