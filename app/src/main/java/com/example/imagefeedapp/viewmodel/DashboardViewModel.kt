package com.example.imagefeedapp.viewmodel


import androidx.lifecycle.ViewModel
import com.example.imagefeedapp.model.LoadingStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class DashboardViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<LoadingStates>(LoadingStates.onLoading);
    val uiState: StateFlow<LoadingStates> =_uiState.asStateFlow();




}