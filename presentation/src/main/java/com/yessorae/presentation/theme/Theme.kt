package com.yessorae.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val LightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    primaryContainer = LightGray,
    onPrimaryContainer = Black,
    secondary = Red,
    onSecondary = White,
    secondaryContainer = LightRed,
    onSecondaryContainer = Black,
    tertiary = Gray,
    onTertiary = White,
    tertiaryContainer = LightGray,
    onTertiaryContainer = Black,
    error = DarkRed,
    onError = White,
    errorContainer = VeryLightRed,
    onErrorContainer = Black,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = VeryLightGray,
    onSurfaceVariant = Black,
    inverseSurface = VeryDarkGray,
    inverseOnSurface = White,
    outline = LightGrayOutline,
)

val DarkColorScheme = darkColorScheme(
    primary = VeryDarkGrayPrimary,
    onPrimary = White,
    primaryContainer = DarkGray,
    onPrimaryContainer = White,
    secondary = Red,
    onSecondary = White,
    secondaryContainer = DarkRedContainer,
    onSecondaryContainer = White,
    tertiary = Gray,
    onTertiary = White,
    tertiaryContainer = DarkGrayTertiaryContainer,
    onTertiaryContainer = White,
    error = LightRedError,
    onError = Black,
    errorContainer = DarkRedErrorContainer,
    onErrorContainer = White,
    background = VeryDarkGray,
    onBackground = White,
    surface = VeryDarkGray,
    onSurface = White,
    surfaceVariant = DarkGrayTertiaryContainer,
    onSurfaceVariant = White,
    inverseSurface = White,
    inverseOnSurface = Black,
    outline = DarkGrayOutline,
)

@Composable
fun LezhinAssignmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
