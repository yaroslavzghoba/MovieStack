package com.yaroslavzghoba.datastore.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.yaroslavzghoba.model.util.BackdropQuality
import com.yaroslavzghoba.model.util.PosterQuality
import com.yaroslavzghoba.model.util.ThemeMode

internal object Constants {
    const val USER_PREFS_STORAGE_NAME = "user_preferences"
    val USE_ACHROMATIC_COLORS_KEY = booleanPreferencesKey("use_achromatic_colors")
    const val USE_ACHROMATIC_COLORS_DEFAULT = true
    val DYNAMIC_THEME_KEY = booleanPreferencesKey("dynamic_theme")
    const val DYNAMIC_THEME_DEFAULT = true
    val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
    val THEME_MODE_DEFAULT = ThemeMode.FOLLOW_SYSTEM
    val POSTER_QUALITY_KEY = stringPreferencesKey("poster_quality")
    val POSTER_QUALITY_DEFAULT = PosterQuality.W342
    val BACKDROP_QUALITY_KEY = stringPreferencesKey("backdrop_quality")
    val BACKDROP_QUALITY_DEFAULT = BackdropQuality.W780
}