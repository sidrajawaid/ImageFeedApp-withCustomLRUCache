package com.example.imagefeedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.imagefeedapp.ui.screens.DashboardScreen
import com.example.imagefeedapp.ui.screens.ToolBarItem
import com.example.imagefeedapp.ui.theme.ImageFeedAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageFeedAppTheme {
                Scaffold(topBar =
                    { ToolBarItem(stringResource(R.string.image_feed))
                    },
                    modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    DashboardScreen(innerPadding)
                }
            }
        }
    }
}

