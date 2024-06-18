package com.yaroslavzghoba.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.yaroslavzghoba.datastore.util.Constants
import com.yaroslavzghoba.model.UserPreferences
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
            val themeMode = preferences[Constants.THEME_MODE_KEY]?.let { themeModeName ->
                ThemeMode.valueOf(themeModeName)
            } ?: Constants.THEME_MODE_DEFAULT
            UserPreferences(
                useAchromaticColors = useAchromaticColors,
                themeMode = themeMode,
            )
        }

    override suspend fun updateUserPreferences(userPreferences: UserPreferences) {
        dataStore.edit { preferences ->
            preferences[Constants.USE_ACHROMATIC_COLORS_KEY] = userPreferences.useAchromaticColors
            preferences[Constants.THEME_MODE_KEY] = userPreferences.themeMode.name
        }
    }
}