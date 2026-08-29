package com.example.imagefeedapp.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FooterFetchingItem() {

    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.Blue),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,

    ) {
        CircularProgressIndicator(modifier = Modifier.padding(10.dp),
            color = Color.White);
        Spacer(modifier = Modifier.padding(6.dp));
        Text(text= "Fetching Images...",
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterVertically)            )
    }
}


@Preview
@Composable
fun Preview(){
    FooterFetchingItem()
}