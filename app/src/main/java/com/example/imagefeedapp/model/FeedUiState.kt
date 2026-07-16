package com.example.imagefeedapp.model

sealed class FeedUiState {
    object Loading : FeedUiState()
    data class Success(val message: String) : FeedUiState()
    data class Failure(val errorMessage: String) : FeedUiState()
    data class Cancelled(val cancelMessage: String) : FeedUiState()
}