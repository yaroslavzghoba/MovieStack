package com.yaroslavzghoba.moviestack.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
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
import com.yaroslavzghoba.settings.SettingsScreen
import com.yaroslavzghoba.settings.SettingsViewModel
import com.yaroslavzghoba.watched_movies.WatchedScreen
import com.yaroslavzghoba.watched_movies.WatchedViewModel
import com.yaroslavzghoba.wish_list.WishListScreen
import com.yaroslavzghoba.wish_list.WishListViewModel

@Composable
fun AppNavigation(
    isNavigationBarVisible: (Boolean) -> Unit,
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
                isNavigationBarVisible = isNavigationBarVisible,
                onGetMoreDiscover = {
                    val route = Screen.MovieList(MovieCategory.DISCOVER.name)
                    navController.navigate(route = route)
                },
                onGetMorePopular = {
                    val route = Screen.MovieList(MovieCategory.POPULAR.name)
                    navController.navigate(route = route)
                },
                onGetMoreTopRated = {
                    val route = Screen.MovieList(MovieCategory.TOP_RATED.name)
                    navController.navigate(route = route)
                },
                onGetMoreNowPlaying = {
                    val route = Screen.MovieList(MovieCategory.NOW_PLAYING.name)
                    navController.navigate(route = route)
                },
                onGetMoreUpcoming = {
                    val route = Screen.MovieList(MovieCategory.UPCOMING.name)
                    navController.navigate(route = route)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface),
            )
        }
        composable<Screen.MovieList> {
            isNavigationBarVisible(false)
            val viewModel: MovieListViewModel = hiltViewModel()
            MovieListScreen(
                viewModel = viewModel,
                onReturnBack = { navController.navigateUp() },
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable<Screen.WishList> {
            isNavigationBarVisible(true)
            val viewModel: WishListViewModel = hiltViewModel()
            WishListScreen(
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
            )
        }
        composable<Screen.Settings> {
            isNavigationBarVisible(true)
            val viewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
            )
        }
        composable<Screen.Watched> {
            isNavigationBarVisible(true)
            val viewModel: WatchedViewModel = hiltViewModel()
            WatchedScreen(
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
            )
        }
    }
}