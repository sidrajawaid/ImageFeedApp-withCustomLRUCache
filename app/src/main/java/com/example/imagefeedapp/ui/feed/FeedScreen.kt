package com.example.imagefeedapp.ui.feed

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.domain.model.BitmapResult
import com.example.imagefeedapp.domain.model.ImageDetailModel
import com.example.imagefeedapp.domain.model.ImageModel
import com.example.imagefeedapp.ui.detail.CardItem


@Composable
fun FeedScreen(
    images: List<ImageModel>, bitmapState: Map<String, BitmapResult?>,
    onImageVisible: (String) -> Unit,
    onImageClick: (ImageDetailModel) -> Unit
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp
        ) {
            items(images) { item ->
                CardItem(
                    imageModel = item,
                    bitmapImg = bitmapState[item.smallImageUrl],
                    onVisible = {
                        onImageVisible(item.smallImageUrl)
                    },
                    onClick = {
                        val result = bitmapState[item.smallImageUrl]
                        if (result?.bitmap != null) {
                            onImageClick(ImageDetailModel(item, result))
                        }
                    })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    val sampleImages = listOf(
        ImageModel(
            smallImageUrl = "https://example.com/1.jpg",
            id = "1",
            width = 200,
            height = 200,
            regularImageUrl = ""
        ),
        ImageModel(
            smallImageUrl = "https://example.com/2.jpg",
            id = "1",
            width = 200,
            height = 200,
            regularImageUrl = ""
        ),
        ImageModel(smallImageUrl = "https://example.com/3.jpg", id = "1", width = 200, height = 200,
            regularImageUrl = "")
    )

    val sampleBitmapState = remember {
        mapOf(
            sampleImages[0].smallImageUrl to BitmapResult(createSampleBitmap(), false, ">1s"),
            sampleImages[1].smallImageUrl to BitmapResult(
                null,
                true,
                "~34 ms ago"
            ), // simulates a cache miss
            sampleImages[2].smallImageUrl to BitmapResult(createSampleBitmap(), true, "~34 ms ago")
        )
    }

    FeedScreen(
        images = sampleImages,
        bitmapState = sampleBitmapState,
        onImageVisible = {},
    ) { }


}

private fun createSampleBitmap(): Bitmap {
    return Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).apply {
        eraseColor(Color.LTGRAY)
    }
}