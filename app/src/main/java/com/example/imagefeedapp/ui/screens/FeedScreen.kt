package com.example.imagefeedapp.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.domain.model.ImageModel


@Composable
fun FeedScreen(images:List<ImageModel>, bitmapState: Map<String, Bitmap?>,
               onImageVisible: (String) -> Unit) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        val itemsList = images
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(120.dp), // 2 columns
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        ) {
            items(itemsList) { item ->
                /*CardItem(
                    imageModel = item,
                    bitmap = bitmapState[item.downloadUrl],
                    onVisible = {
                        onImageVisible(item.downloadUrl)
                    })*/
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    val sampleImages = listOf(
        ImageModel(url = "https://example.com/1.jpg", id="1", width = 200, height = 200, downloadUrl = ""),
        ImageModel(url = "https://example.com/2.jpg", id="1", width = 200, height = 200, downloadUrl = ""),
        ImageModel(url = "https://example.com/3.jpg", id="1", width = 200, height = 200, downloadUrl = "")
    )

    val sampleBitmapState = remember {
        mapOf(
            sampleImages[0].url to createSampleBitmap(),
            sampleImages[1].url to null, // simulates a cache miss
            sampleImages[2].url to createSampleBitmap()
        )
    }

    FeedScreen(
        images = sampleImages,
        bitmapState = sampleBitmapState,
        onImageVisible = {}
    )


}

private fun createSampleBitmap(): Bitmap {
    return Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).apply {
        eraseColor(android.graphics.Color.LTGRAY)
    }
}
