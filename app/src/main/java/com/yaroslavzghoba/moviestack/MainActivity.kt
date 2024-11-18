package com.yaroslavzghoba.moviestack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.yaroslavzghoba.common.LocalBackdropQuality
import com.yaroslavzghoba.common.LocalPosterQuality
import com.yaroslavzghoba.model.util.ThemeMode
import com.yaroslavzghoba.moviestack.ui.theme.MovieStackTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = koinViewModel<MainViewModel>()
            val userPreferences by viewModel.userPreferences.collectAsState()
            CompositionLocalProvider(
                LocalPosterQuality provides userPreferences.posterQuality,
                LocalBackdropQuality provides userPreferences.backdropQuality,
            ) {
                MovieStackTheme(
                    useAchromaticColors = userPreferences.useAchromaticColors,
                    isDarkTheme = when (userPreferences.themeMode) {
                        ThemeMode.FOLLOW_SYSTEM -> isSystemInDarkTheme()
                        ThemeMode.LIGHT -> false
                        ThemeMode.DARK -> true
                    },
                    dynamicColor = userPreferences.dynamicTheme,
                ) {
                    ApplicationContent()
                }
            }
        }
    }
}