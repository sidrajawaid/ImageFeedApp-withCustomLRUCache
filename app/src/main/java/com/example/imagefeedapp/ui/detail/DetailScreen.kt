package com.example.imagefeedapp.ui.detail

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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imagefeedapp.R
import com.example.imagefeedapp.domain.model.BitmapResult
import com.example.imagefeedapp.domain.model.ImageDetailModel
import com.example.imagefeedapp.domain.model.ImageModel
import com.example.imagefeedapp.ui.items.CacheStatusBadge


@Composable
fun DetailScreen(detailModel: ImageDetailModel, onBack:()->Unit) {

  //  val usedMB = cacheStats.currentCacheSize / (1024f * 1024f)
    val cac= (detailModel.bitmapResult.bitmap!!.byteCount)/1024
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {

            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Photo#${detailModel.imageModel.id}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        if (detailModel.bitmapResult.bitmap != null) {
            Image(
                bitmap = detailModel.bitmapResult.bitmap!!.asImageBitmap(),
                contentDescription = "detail of image", modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }else {
            Box(modifier = Modifier.fillMaxWidth().height(300.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant))
        }
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = (Alignment.CenterVertically),
            horizontalArrangement = (Arrangement.spacedBy(10.dp))
        ) {
            CacheStatusBadge(detailModel.bitmapResult.isFromCache)
            Text(
                if (detailModel.bitmapResult.isFromCache) "Loaded instantly" else "Loaded from network",
                fontSize = 11.sp
            )
        }
        /*val maxMemory = Runtime.getRuntime().maxMemory()
        val cacheSize = (maxMemory / 8).toInt()/ (1024f * 1024f)*/

        Column (modifier =  Modifier.padding(all = 4.dp)){
                        ImageDetail((R.drawable.image_id), "ImageID",
                            "picsum/${detailModel.imageModel.id}")
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

 /*  val bitmapResult=  BitmapResult("bmp-icon-32x32.bmp,true)
    val bitmapImage= ImageModel("13","photo/picsum/765*567",678,876,"url/1")
DetailScreen(ImageDetailModel(imageModel =bitmapImage,
    bitmapResult = bitmapResult),{})*/
}