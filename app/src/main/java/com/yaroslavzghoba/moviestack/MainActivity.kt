package com.yaroslavzghoba.moviestack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.hilt.navigation.compose.hiltViewModel
import com.yaroslavzghoba.model.util.ThemeMode
import com.yaroslavzghoba.moviestack.ui.theme.MovieStackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            MovieStackTheme(
                useAchromaticColors = viewModel.userPreferences.useAchromaticColors,
                isDarkTheme = when (viewModel.userPreferences.themeMode) {
                    ThemeMode.FOLLOW_SYSTEM -> isSystemInDarkTheme()
                    ThemeMode.LIGHT -> true
                    ThemeMode.DARK -> false
                },
            ) {
                ApplicationContent()
            }
        }
    }
}