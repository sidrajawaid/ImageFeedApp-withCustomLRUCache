package com.example.imagefeedapp.ui.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.imagefeedapp.domain.model.FeedUiState
import com.example.imagefeedapp.domain.model.ImageDetailModel
import com.example.imagefeedapp.ui.detail.DetailScreen
import com.example.imagefeedapp.ui.feed.FeedScreen


@Composable
fun DashboardScreen(
    innerPadding: PaddingValues,
    viewModel: DashboardViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var selectedModel by remember { mutableStateOf<ImageDetailModel?>(null) }

    val bitmapState by viewModel.bitmapState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        if (selectedModel != null) {
            DetailScreen(selectedModel!!, onBack = /*{ selectedModel = null }*/onBack)
        } else {
            when (uiState) {
                is FeedUiState.Loading -> {
                    ShimmerGridPlaceholder()
                }

                is FeedUiState.Success -> {
                    val images = (uiState as FeedUiState.Success).imageModel
                    FeedScreen(
                        images = images,
                        bitmapState = bitmapState,
                        onImageVisible = { small -> viewModel.loadBitmap(small) },
                        onImageClick = { selectedModel = it }

                    )

                }

                is FeedUiState.Failure -> {
                    FailureFeedScreen(onRetry = { viewModel.loadImages() })
                }

                is FeedUiState.Empty -> {
                    EmptyFeedScreen()
                }

                is FeedUiState.NoConnection -> {
                    NoConnectionScreen(onRetry = { viewModel.loadImages() })
                }
            }
        }
    }
}

