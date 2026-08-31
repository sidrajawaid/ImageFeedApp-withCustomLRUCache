package com.example.imagefeedapp.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
            trackColor =  MaterialTheme.colorScheme.surfaceVariant,
            progress = setProgress,
            modifier = Modifier.fillMaxWidth(),
            color = setColor)
    }
}


@Composable
@Preview
fun PreviewProgressView(){
    ProgressView(Color(0xFF4081FF),40f,
        "Used","14.2/32 MB")
}