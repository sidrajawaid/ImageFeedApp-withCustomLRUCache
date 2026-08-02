package com.example.imagefeedapp.domain.model

sealed class FeedUiState {


    object Loading : FeedUiState()
    data class Success(val imageModel: List<ImageModel>) : FeedUiState()
    data class Failure(val message: String) : FeedUiState()
    object NoNetwork: FeedUiState()
    object Empty: FeedUiState()
}