package com.example.imagefeedapp.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.valentinilk.shimmer.shimmer


@Composable
fun ShimmerGridPlaceholder() {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        LazyVerticalStaggeredGrid(
            columns = /*StaggeredGridCells.Adaptive(120.dp)*/
                StaggeredGridCells.Fixed(2),
            modifier = Modifier.weight(1f,false)
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        ) {
            items(10) { // fixed placeholder count, not real data
                Box(
                    modifier = Modifier
                        .height((100..200).random().dp) // vary heights to mimic staggered look
                        .fillMaxWidth()
                        .shimmer()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.LightGray)
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,

            ) {
            CircularProgressIndicator(
                modifier = Modifier.padding(10.dp),
                color = Color.White
            );
            Spacer(modifier = Modifier.padding(6.dp));
            Text(
                text = "Fetching Images...",
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}



@Preview
@Composable
fun Preview() {
        ShimmerGridPlaceholder()
    }