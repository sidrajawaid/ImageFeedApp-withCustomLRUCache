package com.example.imagefeedapp.ui.feed

import androidx.lifecycle.ViewModel
import com.example.imagefeedapp.domain.model.FeedUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<FeedUiState>(FeedUiState.Loading);
    val uiState: StateFlow<FeedUiState> =_uiState.asStateFlow();




}