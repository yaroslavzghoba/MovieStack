package com.yaroslavzghoba.settings

import com.yaroslavzghoba.model.UserPreferences

internal sealed interface SettingsUiEvent {

    data class UserPreferencesChanged(val userPreferences: UserPreferences) : SettingsUiEvent
}