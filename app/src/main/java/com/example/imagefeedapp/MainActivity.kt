package com.example.imagefeedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.imagefeedapp.ui.feed.DashboardScreen
import com.example.imagefeedapp.ui.screens.ToolBarItem
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
                Scaffold(topBar = {
                    ToolBarItem(
                        title = stringResource(R.string.image_feed),
                        onStatsClick = { showStats = !showStats }
                    )
                }
                ) { innerPadding ->
                    if (showStats) {
                        CacheStatsRoute(onBack = { showStats = false })
                    } else {
                        DashboardScreen(innerPadding)
                    }
                }
            }
        }
    }
}



