package com.example.parcial_grupo_4.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LendlyLightColorScheme = lightColorScheme(
    primary = LendlyColors.Interactive.Primary,
    onPrimary = LendlyColors.Contrast.Light,
    primaryContainer = LendlyColors.Interactive.Accent,
    onPrimaryContainer = LendlyColors.Content.Primary,

    secondary = LendlyColors.Interactive.Secondary,
    onSecondary = LendlyColors.Contrast.Light,
    secondaryContainer = LendlyColors.Background.Neutral,
    onSecondaryContainer = LendlyColors.Content.Primary,

    tertiary = LendlyColors.Interactive.Accent,
    onTertiary = LendlyColors.Content.Primary,

    background = LendlyColors.Background.Screen,
    onBackground = LendlyColors.Content.Primary,

    surface = LendlyColors.Background.Elevated,
    onSurface = LendlyColors.Content.Primary,
    surfaceVariant = LendlyColors.Background.Neutral,
    onSurfaceVariant = LendlyColors.Content.Secondary,

    outline = LendlyColors.Border.Neutral,
    outlineVariant = LendlyColors.Border.Overlay,

    error = LendlyColors.Sentiment.Negative,
    onError = LendlyColors.Contrast.Light,

    scrim = LendlyColors.Contrast.Dark,
)

@Composable
fun Parcialgrupo4Theme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = LendlyLightColorScheme,
        typography = LendlyTypography,
        content = content,
    )
}
