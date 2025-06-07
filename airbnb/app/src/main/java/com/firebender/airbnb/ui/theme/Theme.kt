package com.firebender.airbnb.ui.theme

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
    primary = AirbnbPrimary,
    background = AirbnbNeutral100,
    surface = AirbnbNeutral100,
    onPrimary = AirbnbNeutral10,
    onBackground = AirbnbNeutral10,
    onSurface = AirbnbNeutral10
)

private val LightColorScheme = lightColorScheme(
    primary = AirbnbPrimary,
    background = AirbnbNeutral10,
    surface = AirbnbNeutral10,
    onPrimary = AirbnbNeutral10,
    onBackground = AirbnbNeutral100,
    onSurface = AirbnbNeutral100,
    outline = AirbnbNeutral40
)

@Composable
fun AirbnbTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disable dynamic colors to use Airbnb branding
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
