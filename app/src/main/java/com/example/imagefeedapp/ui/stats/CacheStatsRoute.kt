package com.example.imagefeedapp.ui.stats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CacheStatsRoute(
    onBack: () -> Unit,
    viewModel: CacheStateViewModel = hiltViewModel()
) {
    val cacheStats by viewModel.cacheStats.collectAsStateWithLifecycle()

    CacheStatsScreen(
        cacheStats = cacheStats,
        onClearCache = { viewModel.clearCache() },
        onBack = onBack
    )
}