package com.droidspiration.onlineartgallery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidspiration.onlineartgallery.data.network.ArtPieceData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetRandomArtVewModel @Inject constructor(
    private val randomArtUseCase: GetRandomArtUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState<ArtPieceData>>(UIState.Idle)
    val uiState: StateFlow<UIState<ArtPieceData>> = _uiState

    fun getRandomArt() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            val result = randomArtUseCase()
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