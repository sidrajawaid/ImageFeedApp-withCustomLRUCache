package com.example.imagefeedapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.R


@Composable
fun ImageDetail(painterResource: Int, key: String, value: String) {
    Row(horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)) {
    Image(painterResource(painterResource),
        contentDescription = "image description",
        modifier = Modifier.size(20.dp,20.dp))
    Text(text =key, modifier = Modifier.padding(start = 6.dp))
    Text(text = value , modifier = Modifier.padding(start = 10.dp),
        fontWeight = FontWeight.Bold)
}


}

@Composable
@Preview
fun PreviewImageDetail(){
    ImageDetail( (R.drawable.no_image_),"Cache size", "354 MB")
}
