package com.example.imagefeedapp.ui.screens

import RandomHeightCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer


@Composable
fun FeedScreen() {

    Box(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(Color.LightGray)
        .fillMaxWidth()
        .fillMaxHeight()) {
        val itemsList = List(30) { "Item #$it" }
        LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(120.dp), // 2 columns
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
            ) {
            items(itemsList) { item ->
                RandomHeightCard(item)
            }
        }
    }
}
