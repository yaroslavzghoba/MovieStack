package com.yaroslavzghoba.moviestack.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yaroslavzghoba.common.MovieCategory
import com.yaroslavzghoba.common.Screen
import com.yaroslavzghoba.home.HomeScreen
import com.yaroslavzghoba.home.HomeViewModel
import com.yaroslavzghoba.model.WatchedMovie
import com.yaroslavzghoba.movie_details.MovieDetailsScreen
import com.yaroslavzghoba.movie_details.MovieDetailsViewModel
import com.yaroslavzghoba.movie_list.MovieListScreen
import com.yaroslavzghoba.movie_list.MovieListViewModel
import com.yaroslavzghoba.settings.SettingsScreen
import com.yaroslavzghoba.settings.SettingsViewModel
import com.yaroslavzghoba.watched_movies.WatchedScreen
import com.yaroslavzghoba.watched_movies.WatchedViewModel
import com.yaroslavzghoba.wish_list.WishListScreen
import com.yaroslavzghoba.wish_list.WishListViewModel
import org.koin.compose.viewmodel.koinViewModel

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
            val viewModel = koinViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                isNavigationBarVisible = isNavigationBarVisible,
                onGetMovieDetails = { id ->
                    val route = Screen.MovieDetails(id = id)
                    navController.navigate(route = route)
                },
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
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable<Screen.MovieDetails> {
            isNavigationBarVisible(false)
            val viewModel = koinViewModel<MovieDetailsViewModel>()
            MovieDetailsScreen(
                viewModel = viewModel,
                onReturnBackRequest = { navController.navigateUp() },
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable<Screen.MovieList> {
            isNavigationBarVisible(false)
            val viewModel = koinViewModel<MovieListViewModel>()
            MovieListScreen(
                viewModel = viewModel,
                onReturnBack = { navController.navigateUp() },
                onGetMovieDetails = { id ->
                    val route = Screen.MovieDetails(id = id)
                    navController.navigate(route = route)
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable<Screen.WishList> {
            isNavigationBarVisible(true)
            val viewModel = koinViewModel<WishListViewModel>()
            WishListScreen(
                viewModel = viewModel,
                onGetMovieDetails = { id ->
                    val route = Screen.MovieDetails(id = id)
                    navController.navigate(route = route)
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable<Screen.Settings> {
            isNavigationBarVisible(true)
            val viewModel = koinViewModel<SettingsViewModel>()
            SettingsScreen(
                viewModel = viewModel,
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable<Screen.Watched> {
            isNavigationBarVisible(true)
            val viewModel = koinViewModel<WatchedViewModel>()
            WatchedScreen(
                viewModel = viewModel,
                onGetMovieDetails = { movie: WatchedMovie ->
                    val route = Screen.MovieDetails(id = movie.id)
                    navController.navigate(route = route)
                },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}