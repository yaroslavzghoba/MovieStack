package com.yaroslavzghoba.moviestack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ApplicationRepository,
) : ViewModel() {

    private var _userPreferences = MutableStateFlow(UserPreferences())
    val userPreferences = _userPreferences.asStateFlow()

    init {
        repository.launchMovieReleaseNotifications()
        viewModelScope.launch(context = Dispatchers.IO) {
            repository.getUserPreferences().collect {
                _userPreferences.value = it
            }
        }
    }
}