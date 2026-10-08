package com.example.imagefeedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.imagefeedapp.ui.dashboard.DashboardScreen
import com.example.imagefeedapp.ui.dashboard.ToolBarItem
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
                var title by remember  { mutableStateOf("") }
                Scaffold(
                    topBar = {
                        ToolBarItem(
                            screenTitle = title,
                            onStatsClick = { showStats = true }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    if (showStats) {
                       title= stringResource(R.string.image_feed)
                        CacheStatsRoute(onBack = { showStats = false })
                    } else {
                        title = stringResource(R.string.image_feed)
                        DashboardScreen(innerPadding=innerPadding, onBack = { showStats = false })
                    }
                }
            }
        }
    }
}



