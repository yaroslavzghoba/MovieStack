package com.yaroslavzghoba.moviestack.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yaroslavzghoba.common.MovieCategory
import com.yaroslavzghoba.common.Screen
import com.yaroslavzghoba.home.HomeScreen
import com.yaroslavzghoba.home.HomeViewModel
import com.yaroslavzghoba.movie_list.MovieListScreen
import com.yaroslavzghoba.movie_list.MovieListViewModel

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home,
        modifier = modifier,
    ) {
        composable<Screen.Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                onGetMoreDiscover = {
                    navController.navigate(
                        route = Screen.MovieList(movieCategory = MovieCategory.DISCOVER.name)
                    )
                },
                onGetMorePopular = {
                    navController.navigate(
                        route = Screen.MovieList(movieCategory = MovieCategory.POPULAR.name)
                    )
                },
                onGetMoreTopRated = {
                    navController.navigate(
                        route = Screen.MovieList(movieCategory = MovieCategory.TOP_RATED.name)
                    )
                },
                onGetMoreNowPlaying = {
                    navController.navigate(
                        route = Screen.MovieList(movieCategory = MovieCategory.NOW_PLAYING.name)
                    )
                },
                onGetMoreUpcoming = {
                    navController.navigate(
                        route = Screen.MovieList(movieCategory = MovieCategory.UPCOMING.name)
                    )
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable<Screen.MovieList> {
            val viewModel: MovieListViewModel = hiltViewModel()
            MovieListScreen(
                viewModel = viewModel,
                onReturnBack = { navController.navigateUp() },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}