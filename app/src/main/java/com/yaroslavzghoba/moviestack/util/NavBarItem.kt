package com.yaroslavzghoba.moviestack.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yaroslavzghoba.common.Screen
import com.yaroslavzghoba.moviestack.R

sealed class NavBarItem(
    val route: Screen,
    @StringRes val titleRes: Int,
    @DrawableRes val selectedIconRes: Int,
    @DrawableRes val unselectedIconRes: Int,
) {

    data object Home : NavBarItem(
        route = Screen.Home,
        titleRes = R.string.home_nav_bar_label,
        selectedIconRes = R.drawable.baseline_home_filled_24,
        unselectedIconRes = R.drawable.outline_home_24,
    )

    data object WishList : NavBarItem(
        route = Screen.WishList,
        titleRes = R.string.wish_list_nav_bar_label,
        selectedIconRes = R.drawable.baseline_bookmark_24,
        unselectedIconRes = R.drawable.outline_bookmark_border_24,
    )

    data object Watched : NavBarItem(
        route = Screen.Watched,
        titleRes = R.string.watched_nav_bar_label,
        selectedIconRes = R.drawable.baseline_video_library_24,
        unselectedIconRes = R.drawable.outline_video_library_24,
    )
}