package com.example.imagefeedapp.ui.screens

import android.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.domain.model.BitmapResult
import com.example.imagefeedapp.domain.model.ImageModel
import com.example.imagefeedapp.ui.items.CacheStatusBadge

@Composable
fun CardItem(imageModel: ImageModel, bitmapImg: BitmapResult?, onVisible: () -> Unit) {


    LaunchedEffect(imageModel.downloadUrl) {
        onVisible()
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            if (bitmapImg != null) {
                Image(
                    bitmap = (bitmapImg.bitmap)!!.asImageBitmap(),
                    contentDescription = "Photo ${imageModel.id}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }
            Text(
                text = "Photo#:${imageModel.id}",
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "${imageModel.width} * ${imageModel.height}",
                modifier = Modifier.padding(start = 16.dp)
            )
            bitmapImg?.let {
                CacheStatusBadge(isFromCache = it.isFromCache)
            }
        }
    }
}

@Composable
@Preview
fun PreviewCardItem() {
    val sampleImages =
        ImageModel(
            url = "https://example.com/1.jpg",
            id = "1",
            width = 200,
            height = 200,
            downloadUrl = ""
        )

    val sampleBitmapState: Bitmap =
        BitmapFactory.decodeResource(
            LocalContext.current.resources,
            R.drawable.ic_menu_report_image
        )
    val item = BitmapResult(sampleBitmapState, true)
    CardItem(
        imageModel = sampleImages,
        bitmapImg = item,
        onVisible = {}
    )


}

private fun createSampleBitmap(): Bitmap {
    return Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).apply {
        eraseColor(android.graphics.Color.LTGRAY)
    }
}
