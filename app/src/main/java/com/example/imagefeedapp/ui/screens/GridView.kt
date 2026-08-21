package com.example.imagefeedapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun GridView(modifier: Modifier, textColor: Color, label: String, value: String) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier.background(MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(4.dp))
        .padding(16.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp), // Space between items
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, color = textColor, fontSize = 10.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = value , fontSize = 24.sp)
        }
    }
}

@Preview
@Composable
fun PreviewGridView(){
    GridView(modifier = Modifier.wrapContentSize(),Color.Green, "Hit Rate", "87%")
}