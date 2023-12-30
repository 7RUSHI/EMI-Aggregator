package com.vrushi.emiaggregator.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val appGreenLightTheme = lightColorScheme(
    primary = green_primary_40,
    onPrimary = white,
    primaryContainer = green_primary_90,
    onPrimaryContainer = green_primary_10,
    inversePrimary = green_primary_80,
    secondary = green_secondary_40,
    onSecondary = white,
    secondaryContainer = green_secondary_90,
    onSecondaryContainer = green_secondary_10,
    tertiary = green_tertiary_40,
    onTertiary = white,
    tertiaryContainer = green_tertiary_90,
    onTertiaryContainer = green_tertiary_10,
    error = green_error_40,
    onError = white,
    errorContainer = green_error_90,
    onErrorContainer = green_error_10,
    background = green_neutral_99,
    onBackground = green_neutral_10,
    surface = green_neutral_99,
    onSurface = green_neutral_10,
    inverseSurface = green_neutral_20,
    inverseOnSurface = green_neutral_90,
    surfaceVariant = green_neutral_variant_90,
    onSurfaceVariant = green_neutral_variant_30,
    outline = green_neutral_variant_50,
    outlineVariant = green_neutral_variant_80
)

private val appGreenDarkTheme = darkColorScheme(
    primary = green_primary_40,
    onPrimary = white,
    primaryContainer = green_primary_90,
    onPrimaryContainer = green_primary_10,
    inversePrimary = green_primary_80,
    secondary = green_secondary_40,
    onSecondary = white,
    secondaryContainer = green_secondary_90,
    onSecondaryContainer = green_secondary_10,
    tertiary = green_tertiary_40,
    onTertiary = white,
    tertiaryContainer = green_tertiary_90,
    onTertiaryContainer = green_tertiary_10,
    error = green_error_40,
    onError = white,
    errorContainer = green_error_90,
    onErrorContainer = green_error_10,
    background = green_neutral_90,
    onBackground = green_neutral_10,
    surface = green_neutral_99,
    onSurface = green_neutral_10,
    inverseSurface = green_neutral_20,
    inverseOnSurface = green_neutral_90,
    surfaceVariant = green_neutral_variant_90,
    onSurfaceVariant = green_neutral_variant_30,
    outline = green_neutral_variant_50,
    outlineVariant = green_neutral_variant_80
)

@Composable
fun EMIAggregatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val appColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> appGreenDarkTheme
        else -> appGreenLightTheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = appColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = appColorScheme,
        typography = appTypography,
        shapes = appShapes,
        content = content
    )
}