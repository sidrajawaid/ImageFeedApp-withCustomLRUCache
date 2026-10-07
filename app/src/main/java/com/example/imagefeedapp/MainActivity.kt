package com.example.imagefeedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.imagefeedapp.domain.model.ImageDetailModel
import com.example.imagefeedapp.ui.dashboard.DashboardScreen
import com.example.imagefeedapp.ui.dashboard.ToolBarItem
import com.example.imagefeedapp.ui.detail.DetailScreen
import com.example.imagefeedapp.ui.stats.CacheStatsRoute
import com.example.imagefeedapp.ui.theme.ImageFeedAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ImageFeedAppTheme {
                var showStats by remember { mutableStateOf(false) }
                var selectedModel by remember { mutableStateOf<ImageDetailModel?>(null) }

                Scaffold(
                    topBar = {
                        when {
                            selectedModel != null -> ToolBarItem(
                                title = "Photo #${selectedModel!!.imageModel.id}",
                                showBack = true,
                                onBackClick = { selectedModel = null },
                                showStats = false,
                                onStatsClick = {}
                            )
                            showStats -> ToolBarItem(
                                title = "Cache Stats",
                                showBack = true,
                                onBackClick = { showStats = false },
                                showStats = false,
                                onStatsClick = {}
                            )
                            else -> ToolBarItem(
                                title = stringResource(R.string.image_feed),
                                showBack = false,
                                onBackClick = {},
                                showStats = true,
                                onStatsClick = { showStats = true }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when {
                        selectedModel != null -> DetailScreen(
                            detailModel = selectedModel!!
                        )
                        showStats -> CacheStatsRoute()
                        else -> DashboardScreen(innerPadding)
                    }
                }
            }
        }
    }
}



