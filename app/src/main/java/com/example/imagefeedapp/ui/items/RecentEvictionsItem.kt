package com.example.imagefeedapp.ui.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RecentEvictionsItem(startText: String, endString:String) {
    Row(Modifier.padding(12.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(fontSize = 12.sp, text = startText)
        Text(fontSize = 12.sp, text = endString)
    }

}

@Preview
@Composable
fun PreviewRecentEvictionsItem(){
    RecentEvictionsItem("Photo#4", "312KB - 3s ago")

}