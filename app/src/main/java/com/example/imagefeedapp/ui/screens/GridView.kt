package com.example.imagefeedapp.ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.R


@Composable
fun GridView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.
    background(Color(0xFFAAAAAA), shape = RoundedCornerShape(4.dp))
        .padding(16.dp).wrapContentSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp), // Space between items
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hit rate")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "87%")
        }
    }
}

@Preview
@Composable
fun PreviewGridView(){
    GridView()
}