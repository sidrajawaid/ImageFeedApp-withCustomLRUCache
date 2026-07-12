package com.example.imagefeedapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.R


@Composable
fun EmptyFeedScreen() {
    Column(modifier =  Modifier.padding(2.dp,2.dp)
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(painter = painterResource(R.drawable.no_image_),
            contentDescription = "No Image icon", modifier = Modifier.padding(8.dp))
        Text(text = "No Images yet", modifier = Modifier.padding(8.dp),
            fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
        Text(text="Your feed is empty. Pull down to load images.", modifier = Modifier.padding(8.dp))
        OutlinedButton(onClick = {/* Handle search click */},
            modifier = Modifier.padding(8.dp),
            border = BorderStroke(1.dp, Color.Black),
            shape =RoundedCornerShape(8.dp))
        {
            Icon(painter = painterResource(R.drawable.refresh),
                contentDescription = "Refresh button icon")
            Spacer(modifier = Modifier.padding(4.dp))
            Text("Refresh", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun PreviewEmptyFeedScreen(){
    EmptyFeedScreen()
}