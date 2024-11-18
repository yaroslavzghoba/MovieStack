package com.yaroslavzghoba.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: ApplicationRepository,
) : ViewModel() {

    var userPreferences by mutableStateOf(UserPreferences())
        private set

    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            repository.getUserPreferences().collect {
                userPreferences = it
            }
        }
    }

    internal fun onEvent(event: SettingsUiEvent) {
        when (event) {
            is SettingsUiEvent.UserPreferencesChanged -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    repository.updateUserPreferences(event.userPreferences)
                }
            }
        }
    }
}