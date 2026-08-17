package com.example.imagefeedapp.ui.items


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CacheStatusBadge(isFromCache: Boolean) {

    val backgroundColor = if (isFromCache) Color(0xFFEAF3DE) else Color(0xFFFAEEDA)
    val contentColor = if (isFromCache) Color(0xFF3B6D11) else Color(0xFF854F0B)
    val label = if (isFromCache) "cache hit" else "cache miss"

    Row(
        modifier = Modifier
            .padding(start = 8.dp, bottom = 8.dp)
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(8.dp)) {
            drawCircle(color = contentColor, radius = size.minDimension / 2)
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, color = contentColor, fontSize = 11.sp)
    }
}


@Preview
@Composable
fun PreviewCacheStatusBadge() {
    CacheStatusBadge(true)
}