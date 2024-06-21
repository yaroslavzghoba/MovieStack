package com.yaroslavzghoba.settings.util

import androidx.annotation.StringRes
import com.yaroslavzghoba.model.util.ThemeMode
import com.yaroslavzghoba.settings.R

internal sealed class ThemeModeOption(
    themeMode: ThemeMode,
    @StringRes titleRes: Int,
) : Option<ThemeMode>(
    value = themeMode,
    titleRes = titleRes,
) {

    data object FollowSystem : ThemeModeOption(
        themeMode = ThemeMode.FOLLOW_SYSTEM,
        titleRes = R.string.theme_mode_follow_system_option,
    )

    data object Light : ThemeModeOption(
        themeMode = ThemeMode.LIGHT,
        titleRes = R.string.theme_mode_light_option,
    )

    data object Dark : ThemeModeOption(
        themeMode = ThemeMode.DARK,
        titleRes = R.string.theme_mode_dark_option,
    )
}