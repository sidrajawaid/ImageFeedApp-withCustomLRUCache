package com.example.imagefeedapp.ui.detail

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imagefeedapp.R
import com.example.imagefeedapp.domain.model.BitmapResult
import com.example.imagefeedapp.domain.model.ImageDetailModel
import com.example.imagefeedapp.domain.model.ImageModel
import com.example.imagefeedapp.ui.stats.CacheStatusBadge


@Composable
fun DetailScreen(detailModel: ImageDetailModel) {

    //  val usedMB = cacheStats.currentCacheSize / (1024f * 1024f)
    val cac = (detailModel.bitmapResult.bitmap!!.byteCount) / 1024
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            bitmap = detailModel.bitmapResult.bitmap.asImageBitmap(),
            contentScale = ContentScale.FillWidth,
            contentDescription = "detail of image", modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = (Alignment.CenterVertically),
            horizontalArrangement = (Arrangement.spacedBy(10.dp))
        ) {
            CacheStatusBadge(detailModel.bitmapResult.isFromCache)
            Text(

                if (detailModel.bitmapResult.isFromCache) "Loaded instantly"
                else "Loaded from network",
                fontSize = 11.sp, fontWeight = FontWeight.Bold
            )
        }
        /*val maxMemory = Runtime.getRuntime().maxMemory()
        val cacheSize = (maxMemory / 8).toInt()/ (1024f * 1024f)*/

        Column(modifier = Modifier.padding(all = 4.dp).background(color = MaterialTheme.colorScheme.background) ) {
            ImageDetail(
                (R.drawable.image_id), "ImageID",
                "unsplash/${detailModel.imageModel.sequenceNumber}"
            )
            ImageDetail(
                (R.drawable.dimension),
                "Dimensions",
                "${detailModel.imageModel.height} x ${detailModel.imageModel.width} px"
            )

            ImageDetail(
                (R.drawable.cache_size),
                "Cache size",
                "${cac} KB"
            )
            ImageDetail((R.drawable.load_time), "Load time", detailModel.bitmapResult.loadTime)
        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {


      val bitmapResult=  BitmapResult(hardcodedBitmap(),true,"")
          val bitmapImage= ImageModel("13", 1,13,14,"photo/picsum/765*567",
              "photo/picsum/765*567")
   DetailScreen(ImageDetailModel(imageModel =bitmapImage,
       bitmapResult = bitmapResult))
}

fun hardcodedBitmap(scale: Int = 32): Bitmap {
    val w = 8
    val h = 8
    val y = Color.Yellow.toArgb()
    val k = Color.Black.toArgb()
    val t = Color.Transparent.toArgb()

    val pixels = intArrayOf(
        t, t, y, y, y, y, t, t,
        t, y, y, y, y, y, y, t,
        y, y, k, y, y, k, y, y,
        y, y, k, y, y, k, y, y,
        y, y, y, y, y, y, y, y,
        y, k, y, y, y, y, k, y,
        t, y, k, k, k, k, y, t,
        t, t, y, y, y, y, t, t,
    )

    val small = Bitmap.createBitmap(pixels, w, h, Bitmap.Config.ARGB_8888)
    // Scale up without smoothing so the pixels stay crisp
    return Bitmap.createScaledBitmap(small, w * scale, h * scale, false)
}