package com.firebender.airbnb.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Primary70,
    secondary = Neutral70,
    tertiary = Neutral40,
    background = Neutral100,
    surface = Neutral100,
    onPrimary = Neutral10,
    onSecondary = Neutral10,
    onTertiary = Neutral10,
    onBackground = Neutral10,
    onSurface = Neutral10
)

private val LightColorScheme = lightColorScheme(
    primary = Primary70,
    secondary = Neutral70,
    tertiary = Neutral40,
    background = Neutral10,
    surface = Neutral10,
    onPrimary = Neutral10,
    onSecondary = Neutral10,
    onTertiary = Neutral100,
    onBackground = Neutral100,
    onSurface = Neutral100
)

@Composable
fun AirbnbTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled to use Airbnb brand colors
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
