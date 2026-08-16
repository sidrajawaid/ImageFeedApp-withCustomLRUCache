package com.example.imagefeedapp.ui.items


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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


@Composable
fun CacheStatusBadge(isFromCache: Boolean) {

    if (isFromCache) {
        CacheHitView()
    } else {
        CacheMissView()
    }
}

@Composable
fun DrawDot(setColor: Color) {
    Canvas(modifier = Modifier.size(40.dp)) {
        drawCircle(
            color = setColor,
            radius = 20f, // half of dot diameter
            style = Stroke(width = 20f) // no outline
        )
    }
}

@Composable
 fun CacheHitView() {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .background(Color.Gray, shape = RoundedCornerShape(12.dp))
            .wrapContentWidth()

    ) {
        Row(modifier = Modifier.align(alignment = Alignment.TopStart).fillMaxWidth()) {
            DrawDot(Color.Green)
            Text(text = "Cache hit", color = Color.Green, modifier =
                Modifier.wrapContentSize()
                    .align(Alignment.CenterVertically)
                    )
        }
    }
}

@Composable
fun CacheMissView() {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .background(Color.Gray, shape = RoundedCornerShape(20.dp))
            .wrapContentWidth()

    ) {
        Row(modifier = Modifier.align(alignment = Alignment.TopStart)
            .wrapContentWidth()
            .padding(end  =16.dp)) {
            DrawDot(Color.Red)
            Text(text = "Cache miss", color = Color.Red, modifier =
                Modifier.wrapContentSize()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


@Preview
@Composable
fun PreviewCacheStatusBadge() {
    CacheStatusBadge(false)
}