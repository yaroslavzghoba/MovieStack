package com.yaroslavzghoba.wish_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yaroslavzghoba.domain.repository.ApplicationRepository
import com.yaroslavzghoba.model.WishedMovie
import kotlinx.coroutines.launch

class WishListViewModel(
    private val repository: ApplicationRepository,
) : ViewModel() {

    internal var movies by mutableStateOf(emptyList<WishedMovie>())
        private set

    init {
        viewModelScope.launch {
            repository.getAllWishedMovies().collect {
                movies = it
            }
        }
    }
}