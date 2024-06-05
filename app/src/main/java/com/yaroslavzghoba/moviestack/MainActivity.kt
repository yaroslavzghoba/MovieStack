package com.yaroslavzghoba.moviestack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.yaroslavzghoba.home.HomeScreen
import com.yaroslavzghoba.home.HomeViewModel
import com.yaroslavzghoba.moviestack.ui.theme.MovieStackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieStackTheme {
                val viewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}