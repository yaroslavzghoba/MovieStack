package com.yaroslavzghoba.datastore.util

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.yaroslavzghoba.model.util.ThemeMode

internal object Constants {
    const val USER_PREFS_STORAGE_NAME = "user_preferences"
    val USE_ACHROMATIC_COLORS_KEY = booleanPreferencesKey("use_achromatic_colors")
    const val USE_ACHROMATIC_COLORS_DEFAULT = true
    val THEME_MODE_KEY = stringPreferencesKey("theme_mode")
    val THEME_MODE_DEFAULT = ThemeMode.FOLLOW_SYSTEM
}