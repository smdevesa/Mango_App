package com.example.mango_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = MangoOrange,
    secondary = Color(0xFF121212),
    tertiary = Color(0xFF1A1C1B),
    onTertiary = Color.White,
    background = Color(0xFF1A1C1B),
    onPrimary = Color.Black,
    surface = Color(0xFF333333),
    onSurface = Color.White,
    onBackground = Color.White,
    onSecondary = Color.White,
    surfaceContainer = Purple40,
    outline = Color.White,
    outlineVariant = Color(0x001A1C1B)
    )

private val LightColorScheme = lightColorScheme(
    primary = MangoOrange,
    secondary = MangoGray,
    tertiary = MangoGreen,
    background = LightBackgroundColor,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    onBackground = Color.Black,
    surfaceContainer = Purple40,
    outline = Color.Black,
    outlineVariant = Color(0xFFB3AE9F)
)

@Composable
fun Mango_AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
