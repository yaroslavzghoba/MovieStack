package com.yaroslavzghoba.moviestack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import com.yaroslavzghoba.common.animationSpecDefault
import com.yaroslavzghoba.moviestack.component.AppNavigationBar
import com.yaroslavzghoba.moviestack.navigation.AppNavigation
import com.yaroslavzghoba.moviestack.util.NavBarItem

@Composable
fun ApplicationContent(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val navBarItems = listOf(NavBarItem.Home, NavBarItem.WishList, NavBarItem.Watched)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val destinations: List<NavDestination> by lazy {
        navBarItems.map { navController.graph[it.route] }
    }
    var isNavBarVisible by remember { mutableStateOf(true) }

    Column(modifier = modifier) {
        AppNavigation(
            isNavigationBarVisible = { isNavBarVisible = it },
            navController = navController,
            modifier = Modifier.weight(1f),
        )

        // Navigation bar
        AnimatedVisibility(
            visible = isNavBarVisible,
            enter = expandVertically(animationSpec = animationSpecDefault()),
            exit = shrinkVertically(animationSpec = animationSpecDefault())
        ) {
            AppNavigationBar(
                items = navBarItems,
                selected = { itemIndex ->
                    destinations[itemIndex].id == currentDestination?.id
                },
                onItemClicked = { item ->
                    navController.navigate(route = item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}