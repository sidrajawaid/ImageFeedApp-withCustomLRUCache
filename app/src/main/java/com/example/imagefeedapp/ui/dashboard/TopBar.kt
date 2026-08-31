package com.example.imagefeedapp.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarItem(screenTitle: String, onStatsClick: () -> Unit){


    TopAppBar(
        title={Text(text = screenTitle, style = MaterialTheme.typography.titleMedium)},
        navigationIcon = {
            IconButton(onClick = { /*rememberNavController().navigateUp()*/ }) {
                Icon(Icons.Default.ArrowBack,
                    contentDescription = "Back Icon",
                    modifier = Modifier.wrapContentWidth(align = Alignment.Start))
            }
        },
        actions = {IconButton(onClick =  onStatsClick ) {
            Icon(Icons.Default.GridView,"Search")
        }},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFF6200EE),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )

}






