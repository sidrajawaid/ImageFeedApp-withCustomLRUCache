package com.example.imagefeedapp.data.cache

import android.graphics.Bitmap
import com.example.imagefeedapp.domain.model.BitmapResult
import com.example.imagefeedapp.domain.model.CacheStats
import com.example.imagefeedapp.domain.model.EvictionRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Cache


class LRUCache(val cacheSize: Int) {

    private val MAX_EVICTION_HISTORY=10
    private var map: LinkedHashMap<String, CacheEntry> = LinkedHashMap(0, 0.75f, true)

    private var currentSize = 0
    private var hitUrls = 0
    private var missUrls = 0;
    private var deletedUrls = 0

    private val _cacheCleared = MutableStateFlow<Int>(0)
    val cacheCleared: StateFlow<Int> = _cacheCleared.asStateFlow()


    private val evictionHistory = ArrayDeque<EvictionRecord>(10)

    @Synchronized
    fun addUrlEntry(url: String, bitmap: Bitmap, sequenceNumber: Int) {

        if (bitmap.byteCount > cacheSize)
            return
        val existing = map[url]
        if (existing != null) {
            currentSize -= existing.bitmap.byteCount   // or existing.byteCount before the holder class
        }

        while (currentSize + bitmap.byteCount > cacheSize) {

            deleteUrlEntry()
        }
        map[url] = CacheEntry(bitmap, sequenceNumber)
        currentSize += bitmap.byteCount

    }

    @Synchronized
    fun getBitmap(url: String): Bitmap? {
        val entry = map[url]
        if (entry != null) {
            hitUrls++    // cache hit
        } else {
            missUrls++   // cache miss
        }
        return entry?.bitmap
    }

    @Synchronized
    private fun deleteUrlEntry() {

        val firstEntry = map.entries.iterator().next()
        currentSize -= firstEntry.value.bitmap.byteCount
        deletedUrls++
        if (evictionHistory.size >= MAX_EVICTION_HISTORY)
            evictionHistory.removeFirst()
        evictionHistory.addLast(
            EvictionRecord(
                url = firstEntry.key,
                sequenceNumber = firstEntry.value.sequenceNumber,
                sizeBytes = firstEntry.value.bitmap.byteCount
            )
        )
        map.remove(firstEntry.key)

    }

    @Synchronized
    fun clear() {
        map.clear()
        hitUrls = 0
        missUrls = 0
        deletedUrls = 0
        evictionHistory.clear()
        currentSize = 0
        _cacheCleared.value += 1
    }


    @Synchronized
    fun getCacheStats(): CacheStats {
        val hitRate = if (hitUrls + missUrls == 0) 0f
        else hitUrls.toFloat() / (hitUrls + missUrls)

        return CacheStats(
            currentCacheSize = currentSize,
            maxSize = cacheSize,
            hitCount = hitUrls,
            missCount = missUrls,
            evictionCount = deletedUrls,
            hitRate = hitRate,
            recentEvictions = evictionHistory.toList()
        )

    }

    private class CacheEntry(
        val bitmap: Bitmap,
        val sequenceNumber: Int
    )
}