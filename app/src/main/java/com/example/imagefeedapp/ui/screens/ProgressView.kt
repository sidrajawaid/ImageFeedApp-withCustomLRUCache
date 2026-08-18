package com.example.imagefeedapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.R


@Composable
fun ProgressView(setColor:Color, setProgress:Float,
                 startText: String, endText:String) {

    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = startText
            )
            Text(
                text = endText
            )
        }

        LinearProgressIndicator(
            trackColor = Color(0xFF4081FF), progress = setProgress,
            modifier = Modifier.fillMaxWidth(),
            color = Color(LocalContext.current.resources.getColor(R.color.grey))
        )
    }
}


@Composable
@Preview
fun PreviewProgressView(){
    ProgressView(Color(0xFF4081FF),40f,
        "Used","14.2/32 MB")
}