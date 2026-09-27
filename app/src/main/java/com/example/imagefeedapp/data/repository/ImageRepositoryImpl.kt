package com.example.imagefeedapp.data.repository

import com.example.imagefeedapp.data.mapper.toImageModel
import com.example.imagefeedapp.data.network.NetworkMonitor
import com.example.imagefeedapp.data.remote.PicsumApiService
import com.example.imagefeedapp.data.remote.UnsplashApiService
import com.example.imagefeedapp.domain.repository.ImageRepository
import com.example.imagefeedapp.domain.model.FeedUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val unSplashApiService: UnsplashApiService,
    private val networkMonitor: NetworkMonitor
) : ImageRepository {
    override fun getImages(): Flow<FeedUiState> = flow {
        val isConnected = networkMonitor.isConnected.first()

        if (!isConnected) {
            emit(FeedUiState.NoConnection)
            return@flow  // stop here, no point continuing
        }
        emit(FeedUiState.Loading)

        try {
            val images = unSplashApiService.getImageInformation(page = 1, perPage = 50)
                .map { it.toImageModel() }
            emit(FeedUiState.Success(images))
        } catch (e: Exception) {
            emit(FeedUiState.Failure("Error Occurred"))
        }
    }
}