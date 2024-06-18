package com.yaroslavzghoba.moviestack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.yaroslavzghoba.moviestack.ui.theme.MovieStackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieStackTheme(
                useAchromaticColors = true,  // TODO: Provide the property from user prefs
                isDarkTheme = isSystemInDarkTheme(),  // TODO: Provide the property from user prefs
            ) {
                ApplicationContent()
            }
        }
    }
}