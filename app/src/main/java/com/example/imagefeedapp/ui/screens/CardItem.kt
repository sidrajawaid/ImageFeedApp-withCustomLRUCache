package com.example.imagefeedapp.ui.screens

import android.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.domain.model.ImageModel
import kotlin.random.Random

@Composable
fun CardItem( imageModel: ImageModel, bitmapImg: Bitmap?, onVisible: () -> Unit) {

    val randomHeight = Random.nextInt(120, 200).dp
    Card(
        modifier = Modifier
            .width(200.dp)
            .wrapContentHeight(),
    ) {

        Column(
            modifier = Modifier.wrapContentSize().padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Image(
                bitmap = bitmapImg!!.asImageBitmap(),
                contentDescription = "feed image",
                modifier = Modifier.fillMaxWidth().height(100.dp)
            )
            Text(text = "Photo#:${imageModel.id}",
                modifier = Modifier.padding(start = 16.dp))
            Text(text = "${imageModel.width} * ${imageModel.height}"
                ,modifier = Modifier.padding(start = 16.dp))
            Text(text = "Sidra",
                modifier = Modifier.padding(start = 16.dp))
        }
    }
}

@Composable
@Preview
fun PreviewCardItem(){
    val sampleImages =
        ImageModel(url = "https://example.com/1.jpg", id="1", width = 200, height = 200, downloadUrl = "")

    val sampleBitmapState: Bitmap =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.ic_menu_report_image)

    CardItem(
        imageModel = sampleImages,
        bitmapImg = sampleBitmapState,
        onVisible = {}
    )


}

private fun createSampleBitmap(): Bitmap {
    return Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888).apply {
        eraseColor(android.graphics.Color.LTGRAY)
    }
}
