package com.droidspiration.onlineartgallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtRecordsViewModel @Inject constructor(
    private val artRecordsUseCase: GetArtRecordsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState<List<Int>>>(UIState.Idle)
    val uiState: StateFlow<UIState<List<Int>>> = _uiState

    fun searchArt(query: String) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            val result = artRecordsUseCase(query)
            when (result) {
                is Result.Success -> {
                    // Handle success case, e.g., navigate to Show Random Art Screen
                     _uiState.value = UIState.Success(result.data)
                }
                is Result.Error -> {
                    _uiState.value = UIState.Error(result.exception.message ?: "Error fetching records")
                }
                Result.Loading -> {
                    _uiState.value = UIState.Loading // Already handled
                }
            }
        }
    }
}