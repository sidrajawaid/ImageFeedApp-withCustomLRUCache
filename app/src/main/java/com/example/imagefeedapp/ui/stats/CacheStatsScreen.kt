package com.example.imagefeedapp.ui.stats

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imagefeedapp.domain.model.CacheStats
import com.example.imagefeedapp.ui.items.RecentEvictionsItem
import com.example.imagefeedapp.ui.screens.GridView
import com.example.imagefeedapp.ui.screens.ProgressView


@Composable
fun CacheStatsScreen(
    cacheStats: CacheStats,
    onClearCache: () -> Unit,
    onBack: () -> Unit
) {

    val usedMB = cacheStats.currentCacheSize / (1024f * 1024f)
    val maxMB = cacheStats.maxSize / (1024f * 1024f)

    val memoryUsagePercent = if (cacheStats.maxSize == 0) 0f
    else cacheStats.currentCacheSize.toFloat() / cacheStats.maxSize

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
            .background(Color(0xFFFFFFFF))
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            GridView(modifier = Modifier.weight(1f),Color(0xFF954646FF), "Hit Rate", "%.0f%%\".format(cacheStats.hitRate * 100)")
            GridView(modifier = Modifier.weight(1f),Color(0xFF5B7837FF), "Cache size", "%.1f MB".format(usedMB))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            GridView(modifier = Modifier.weight(1f),Color(0xFF5B7837FF), "Hits", cacheStats.hitCount.toString())
            GridView(modifier = Modifier.weight(1f),Color(0xFF5B7837FF), "Misses", cacheStats.missCount.toString())


        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .wrapContentHeight()
        )
        {
            Text(text = "MEMORY USAGE", modifier = Modifier.fillMaxWidth(), fontStyle = FontStyle.Italic)
            ProgressView(Color.Blue, usedMB, "Used", memoryUsagePercent.toString())
            ProgressView(Color.Green, (cacheStats.hitRate * 100), "Hit rate", "${cacheStats.hitRate}%")
            Text("RECENT EVICTIONS", modifier = Modifier.padding(top=8.dp), fontStyle = FontStyle.Italic)

            LazyColumn(modifier = Modifier.height(150.dp))
            {
                items(cacheStats.recentEvictions){
                    item->
                    RecentEvictionsItem("Photo#${item.url.split("/").getOrNull(5) ?: item.url}",
                        "${item.sizeBytes / 1024}- ${(System.currentTimeMillis() - item.timestamp) / 1000}")
                }
            }
            }
        OutlinedButton(onClick = onClearCache, modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(2.dp,Color.Red))  {
            Text("Clear Cache", color = Color.Red)
        }
        }

    }


@Preview
@Composable
fun PreviewCacheStatsScreen() {
    CacheStatsScreen(
        cacheStats = CacheStats(
            currentCacheSize = 14000000,
            maxSize = 32000000,
            hitCount = 124,
            missCount = 18,
            evictionCount = 3,
            hitRate = 0.87f
        ),
        onClearCache = {},
        onBack = {}
    )
}