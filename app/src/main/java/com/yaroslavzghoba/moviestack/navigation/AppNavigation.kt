package com.yaroslavzghoba.moviestack.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import com.yaroslavzghoba.common.MovieCategory
import com.yaroslavzghoba.common.Screen
import com.yaroslavzghoba.home.HomeScreen
import com.yaroslavzghoba.home.HomeViewModel
import com.yaroslavzghoba.movie_list.MovieListScreen
import com.yaroslavzghoba.movie_list.MovieListViewModel
import com.yaroslavzghoba.moviestack.util.NavBarItem
import com.yaroslavzghoba.watched_movies.WatchedScreen
import com.yaroslavzghoba.watched_movies.WatchedViewModel
import com.yaroslavzghoba.wish_list.WishListScreen
import com.yaroslavzghoba.wish_list.WishListViewModel

private const val TAG = "AppNavigation"

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBarItems = listOf(NavBarItem.Home, NavBarItem.WishList, NavBarItem.Watched)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val navBarDestinations: List<NavDestination> by lazy {
        navBarItems.map { navController.graph[it.route] }
    }

    Column(modifier = modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home,
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.background),
        ) {
            composable<Screen.Home> {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(
                    viewModel = viewModel,
                    onGetMoreDiscover = {
                        navController.navigate(
                            route = Screen.MovieList(MovieCategory.DISCOVER.name)
                        )
                    },
                    onGetMorePopular = {
                        navController.navigate(
                            route = Screen.MovieList(MovieCategory.POPULAR.name)
                        )
                    },
                    onGetMoreTopRated = {
                        navController.navigate(
                            route = Screen.MovieList(MovieCategory.TOP_RATED.name)
                        )
                    },
                    onGetMoreNowPlaying = {
                        navController.navigate(
                            route = Screen.MovieList(MovieCategory.NOW_PLAYING.name)
                        )
                    },
                    onGetMoreUpcoming = {
                        navController.navigate(
                            route = Screen.MovieList(MovieCategory.UPCOMING.name)
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
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
            composable<Screen.WishList> {
                val viewModel: WishListViewModel = hiltViewModel()
                WishListScreen(
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                )
            }
            composable<Screen.Watched> {
                val viewModel: WatchedViewModel = hiltViewModel()
                WatchedScreen(
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                )
            }
        }

        // Navigation bar
        if (navBarDestinations.any { it.id == currentDestination?.id }) {
            NavigationBar {
                navBarItems.forEachIndexed { index, navBarItem ->
                    val selected = navBarDestinations[index].id == currentDestination?.id
                    val iconRes = when (selected) {
                        true -> navBarItem.selectedIconRes
                        false -> navBarItem.unselectedIconRes
                    }
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navController.navigate(route = navBarItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = iconRes),
                                contentDescription = null,
                            )
                        },
                        label = { Text(text = stringResource(id = navBarItem.titleRes)) },
                    )
                }
            }
        }
    }
}