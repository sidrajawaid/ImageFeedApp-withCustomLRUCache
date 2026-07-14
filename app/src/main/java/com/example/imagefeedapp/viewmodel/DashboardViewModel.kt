package com.example.imagefeedapp.viewmodel


import androidx.lifecycle.ViewModel
import com.example.imagefeedapp.model.LoadingStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


@HiltViewModel
class DashboardViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<LoadingStates>(LoadingStates.onLoading);
    val uiState: StateFlow<LoadingStates> =_uiState.asStateFlow();




}