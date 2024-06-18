package com.yaroslavzghoba.model

import com.yaroslavzghoba.model.util.ThemeMode

/**
 * Represent user preferences
 *
 * @param useAchromaticColors specify does the application theme use achromatic colors in design
 * instead of original Material Design 3 colorful palette
 * @param themeMode specify which theme mode does application use (dark or light)
 */
data class UserPreferences(
    val useAchromaticColors: Boolean = true,
    val themeMode: ThemeMode = ThemeMode.FOLLOW_SYSTEM,
)
