package com.example.imagefeedapp.ui.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.imagefeedapp.domain.model.FeedUiState
import com.example.imagefeedapp.ui.screens.EmptyFeedScreen
import com.example.imagefeedapp.ui.screens.FailureFeedScreen


@Composable
    fun DashboardScreen(
        innerPadding: PaddingValues,
        viewModel: DashboardViewModel = hiltViewModel()
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val isConnected by viewModel.isConnected.collectAsStateWithLifecycle()
        val bitmapState by viewModel.bitmapState.collectAsStateWithLifecycle()

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

                when (uiState) {
                    is FeedUiState.Loading -> {}// show loading composable
                    is FeedUiState.Success -> {}// show feed grid, pass images and bitmapState
                    is FeedUiState.Failure -> {
                        FailureFeedScreen()
                    }// show empty feed screen
                    is FeedUiState.Empty -> {EmptyFeedScreen()}// show empty feed screen
                    is FeedUiState.NoNetwork -> {}// show no connection screen
                }
        }
}

@Preview
@Composable
fun Preview(){
    val fakePadding = PaddingValues(
        top = 56.dp,    // typical TopAppBar height
        bottom = 16.dp, // e.g., bottom nav or FAB spacing
        start = 8.dp,
        end = 8.dp
    )
    DashboardScreen(fakePadding)
}