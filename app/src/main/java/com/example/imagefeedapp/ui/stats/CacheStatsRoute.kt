package com.example.imagefeedapp.ui.stats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CacheStatsRoute(
    sequenceNumber: Int,
    viewModel: CacheStateViewModel = hiltViewModel()
) {
    val cacheStats by viewModel.cacheStats.collectAsStateWithLifecycle()

    CacheStatsScreen(
        sequenceNumber = sequenceNumber,
        cacheStats = cacheStats,
        onClearCache = { viewModel.clearCache() },

        )
}