package com.example.imagefeedapp.domain.repository

import com.example.imagefeedapp.domain.model.FeedUiState
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    fun getImages(): Flow<FeedUiState>
}