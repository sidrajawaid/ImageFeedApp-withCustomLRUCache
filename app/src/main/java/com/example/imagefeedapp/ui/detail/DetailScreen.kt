package com.example.imagefeedapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imagefeedapp.R
import com.example.imagefeedapp.domain.model.ImageDetailsList
import com.example.imagefeedapp.ui.items.CacheStatusBadge


@Composable
fun DetailScreen(onBack: () -> Unit, imageDetail: ImageDetailsList) {
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
                text = "Phot#12",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Image(
            painter = painterResource(R.drawable.no_image_),
            contentDescription = "detail of image", modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = (Alignment.CenterVertically),
            horizontalArrangement = (Arrangement.spacedBy(10.dp))
        ) {
            CacheStatusBadge(true)
            Text("Loaded instantly", fontSize = 11.sp)
        }

        LazyColumn() {
            items(imageDetail) { item ->
                ImageDetail(item)
            }
        }


    }
}

@Preview
@Composable
fun PreviewDeatilScreen() {
 //   DetailScreen(onBack = {},)
}