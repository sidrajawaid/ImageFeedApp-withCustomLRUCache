package com.example.imagefeedapp.ui.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.imagefeedapp.domain.model.FeedUiState
import com.example.imagefeedapp.ui.items.FooterFetchingItem
import com.example.imagefeedapp.ui.screens.EmptyFeedScreen
import com.example.imagefeedapp.ui.screens.FailureFeedScreen
import com.example.imagefeedapp.ui.screens.FeedScreen
import com.example.imagefeedapp.ui.screens.NoConnectionScreen
import com.example.imagefeedapp.ui.stats.CacheStatsRoute


@Composable
    fun DashboardScreen(
        innerPadding: PaddingValues,
        viewModel: DashboardViewModel = hiltViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        val bitmapState by viewModel.bitmapState.collectAsStateWithLifecycle()

        Box(
            modifier = Modifier
                .padding(innerPadding)

                .fillMaxSize()){
                when (uiState) {
                    is FeedUiState.Loading -> {
                        FooterFetchingItem()
                    }
                    is FeedUiState.Success -> {
                        val images = (uiState as FeedUiState.Success).imageModel
                        FeedScreen(
                            images = images,
                            bitmapState = bitmapState,
                            onImageVisible = { url -> viewModel.loadBitmap(url) }
                        )

                    }
                    is FeedUiState.Failure -> {
                        FailureFeedScreen(onRetry = {viewModel.loadImages()})
                    }// show empty feed screen
                    is FeedUiState.Empty -> {EmptyFeedScreen()}
                    is FeedUiState.NoConnection -> {
                        NoConnectionScreen()
                    }// show no connection screen
                }
        }
}

