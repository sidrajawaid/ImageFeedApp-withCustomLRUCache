package com.example.imagefeedapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.ui.items.FooterFetchingItem


@Composable
fun DashboardScreen(innerPadding: PaddingValues) {

    Box(modifier = Modifier.padding(innerPadding).wrapContentHeight().wrapContentWidth()) {
        Column() {
            FeedScreen()
            FooterFetchingItem()
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