package com.yaroslavzghoba.moviestack.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.yzghoba.achromatic.AchromaticTheme
import com.yzghoba.achromatic.color.darkAchromaticScheme
import com.yzghoba.achromatic.color.lightAchromaticScheme

@Composable
fun MovieStackTheme(
    useAchromaticColors: Boolean,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Original Material Design 3 color scheme
    // Will be applied if useAchromaticColors is false
    // TODO: Import color palette from Material Theme Builder
    // TODO: Consider usage DynamicTheme library created by T8RIN
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    // Achromatic color scheme
    // Will be applied if useAchromaticColors is true
    val achromaticScheme = when {
        darkTheme -> darkAchromaticScheme()
        else -> lightAchromaticScheme()
    }


    AchromaticTheme(
        useAchromaticColors = useAchromaticColors,
        colorScheme = colorScheme,
        achromaticScheme = achromaticScheme,
        typography = Typography,
        content = content
    )
}