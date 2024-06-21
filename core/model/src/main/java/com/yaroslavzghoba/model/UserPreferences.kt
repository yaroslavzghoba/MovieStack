package com.yaroslavzghoba.model

import com.yaroslavzghoba.model.util.BackdropQuality
import com.yaroslavzghoba.model.util.PosterQuality
import com.yaroslavzghoba.model.util.ThemeMode

/**
 * Represent user preferences
 *
 * @param useAchromaticColors specify does the application theme use achromatic colors in design
 * instead of original Material Design 3 colorful palette
 * @param dynamicTheme specify does the application use a color palette
 * matched to the user's wallpaper (doesn't work if useAchromaticColors is true)
 * @param themeMode specify which theme mode does application use
 * @param posterQuality specify movie posters quality
 * @param backdropQuality specify movie backdrops quality
 */
data class UserPreferences(
    val useAchromaticColors: Boolean = true,
    val dynamicTheme: Boolean = true,
    val themeMode: ThemeMode = ThemeMode.FOLLOW_SYSTEM,
    // TODO: Implement auto selection based on user's Internet speed
    val posterQuality: PosterQuality = PosterQuality.W342,
    // TODO: Implement auto selection based on user's Internet speed
    val backdropQuality: BackdropQuality = BackdropQuality.W780,
)
