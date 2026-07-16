package com.example.imagefeedapp.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagefeedapp.model.FeedUiState
import com.example.imagefeedapp.network.observeNetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow<FeedUiState>(FeedUiState.Loading)
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    private val _isConnected = MutableStateFlow<Boolean>(false)

    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    init{
        viewModelScope.launch {

            context.observeNetworkState().collect { connection ->
                _isConnected.value=connection;
            }

        }
    }

    fun retryFetch() {
        _uiState.value = FeedUiState.onLoading
    }



}