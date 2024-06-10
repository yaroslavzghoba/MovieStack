package com.yaroslavzghoba.moviestack.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.yaroslavzghoba.common.Screen
import com.yaroslavzghoba.moviestack.R

sealed class NavBarItem(
    val route: Screen,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
) {

    data object Home : NavBarItem(
        route = Screen.Home,
        titleRes = R.string.home_nav_bar_label,
        iconRes = R.drawable.baseline_home_24,
    )

    data object WishList : NavBarItem(
        route = Screen.WishList,
        titleRes = R.string.wish_list_nav_bar_label,
        iconRes = R.drawable.baseline_bookmark_24,
    )

    data object Watched : NavBarItem(
        route = Screen.Watched,
        titleRes = R.string.watched_nav_bar_label,
        iconRes = R.drawable.baseline_video_library_24,
    )
}