package com.yaroslavzghoba.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.yaroslavzghoba.datastore.util.Constants
import com.yaroslavzghoba.model.UserPreferences
import com.yaroslavzghoba.model.util.BackdropQuality
import com.yaroslavzghoba.model.util.PosterQuality
import com.yaroslavzghoba.model.util.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPrefsRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
) : UserPrefsRepository {

    override fun getUserPreferences(): Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            when (exception) {
                is IOException -> emit(emptyPreferences())
                else -> throw exception
            }
        }
        .map { preferences ->
            val useAchromaticColors = preferences[Constants.USE_ACHROMATIC_COLORS_KEY]
                ?: Constants.USE_ACHROMATIC_COLORS_DEFAULT
            val dynamicTheme = preferences[Constants.DYNAMIC_THEME_KEY]
                ?: Constants.DYNAMIC_THEME_DEFAULT
            val themeMode = preferences[Constants.THEME_MODE_KEY]
                ?.let { ThemeMode.valueOf(it) }
                ?: Constants.THEME_MODE_DEFAULT
            val posterQuality = preferences[Constants.POSTER_QUALITY_KEY]
                ?.let { PosterQuality.valueOf(it) }
                ?: Constants.POSTER_QUALITY_DEFAULT
            val backdropQuality = preferences[Constants.BACKDROP_QUALITY_KEY]
                ?.let { BackdropQuality.valueOf(it) }
                ?: Constants.BACKDROP_QUALITY_DEFAULT
            UserPreferences(
                useAchromaticColors = useAchromaticColors,
                dynamicTheme = dynamicTheme,
                themeMode = themeMode,
                posterQuality = posterQuality,
                backdropQuality = backdropQuality,
            )
        }

    override suspend fun updateUserPreferences(userPreferences: UserPreferences) {
        dataStore.edit { preferences ->
            preferences[Constants.USE_ACHROMATIC_COLORS_KEY] = userPreferences.useAchromaticColors
            preferences[Constants.DYNAMIC_THEME_KEY] = userPreferences.dynamicTheme
            preferences[Constants.THEME_MODE_KEY] = userPreferences.themeMode.name
            preferences[Constants.POSTER_QUALITY_KEY] = userPreferences.posterQuality.name
            preferences[Constants.BACKDROP_QUALITY_KEY] = userPreferences.backdropQuality.name
        }
    }
}