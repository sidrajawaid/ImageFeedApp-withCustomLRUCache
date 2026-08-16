package com.example.imagefeedapp.ui.feed

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagefeedapp.data.loader.BitmapLoader
import com.example.imagefeedapp.data.network.NetworkMonitor
import com.example.imagefeedapp.domain.model.BitmapResult
import com.example.imagefeedapp.domain.model.FeedUiState
import com.example.imagefeedapp.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val networkMonitor: NetworkMonitor,
    private val bitmapLoader: BitmapLoader
) : ViewModel() {


    private val _isConnected = MutableStateFlow(false)

    private val _bitmapState = MutableStateFlow<Map<String, BitmapResult?>>(emptyMap())
    val bitmapState: StateFlow<Map<String, BitmapResult?>> = _bitmapState.asStateFlow()
    private val _uiState = MutableStateFlow<FeedUiState>(FeedUiState.Loading)
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collect { connected ->
                _isConnected.value = connected
            }
        }
        loadImages()

    }

     fun loadImages() {
        viewModelScope.launch {
            getImagesUseCase.invoke().collect { result ->
                _uiState.value = result
            }
        }
    }

    fun loadBitmap(url: String) {
        viewModelScope.launch {
            val bitmap = bitmapLoader.loadBitmap(url)
            _bitmapState.value += (url to bitmap)
        }
    }
}


