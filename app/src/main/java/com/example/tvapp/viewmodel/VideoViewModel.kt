package com.example.tvapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvapp.data.api.ApiClient
import com.example.tvapp.data.model.Video
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class VideoUiState(
    val videos: List<Video> = emptyList(),
    val searchResults: List<Video> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class VideoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(VideoUiState())
    val uiState: StateFlow<VideoUiState> = _uiState.asStateFlow()

    private val apiKey = "GKNDJjingRvTNexvVuYW05cBZaKU2XaM9Z3ExL68kGev9iZy0pU24l4F"

    init {
        loadVideos()
    }

    private fun loadVideos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val response = ApiClient.api.getPopularVideos(apiKey)
                _uiState.value = _uiState.value.copy(
                    videos = response.videos,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun searchVideos(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val response = ApiClient.api.searchVideos(apiKey, query)
                _uiState.value = _uiState.value.copy(
                    searchResults = response.videos,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun retry() {
        loadVideos()
    }
}