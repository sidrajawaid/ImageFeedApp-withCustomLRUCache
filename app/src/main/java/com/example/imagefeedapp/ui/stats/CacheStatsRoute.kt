package com.example.imagefeedapp.ui.stats

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CacheStatsRoute(paddingValues: PaddingValues,
    sequenceNumber: Int,
    viewModel: CacheStateViewModel = hiltViewModel()
) {
    val cacheStats by viewModel.cacheStats.collectAsStateWithLifecycle()

    CacheStatsScreen(paddingValues,
        sequenceNumber = sequenceNumber,
        cacheStats = cacheStats,
        onClearCache = { viewModel.clearCache() },

        )
}