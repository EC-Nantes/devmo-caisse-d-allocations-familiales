package com.example.devmo_caisse_d_allocations_familiales.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = CafPrimary,
    secondary = CafTurquoise,
    background = CafWhite,
    surface = CafWhite,
    onPrimary = CafWhite,
    onSecondary = CafWhite,
    onBackground = CafText,
    onSurface = CafText
)

@Composable
fun DevmoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        content = content
    )
}