package com.example.imagefeedapp.ui.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

Column(verticalArrangement = Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize()){}
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column() {
            GridView(Color(0xFF954646FF), "Hit Rate", cacheStats.hitRate.toString())
            GridView(Color(0xFF5B7837FF), "Misses", cacheStats.missCount.toString())
        }
        Column() {
            GridView(Color(0xFF5B7837FF), "Hits", cacheStats.hitCount.toString())
            GridView(Color(0xFF5B7837FF), "Cache size", cacheStats.currentCacheSize.toString())

        }
    }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentHeight()) {
        Text(text = "Memory Usage", modifier = Modifier.fillMaxWidth())
        ProgressView(Color.Red, 40f, "", "")
        ProgressView(Color.Red, 40f, "", "")
        Text("Recent Eviction")
        RecentEvictionsItem("Photo#4", "312KB - 3s ago")
        RecentEvictionsItem("Photo#4", "312KB - 3s ago")
        RecentEvictionsItem("Photo#4", "312KB - 3s ago")
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