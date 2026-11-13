package com.example.imagefeedapp.data.cache

import android.graphics.Bitmap
import com.example.imagefeedapp.domain.model.CacheStats
import com.example.imagefeedapp.domain.model.EvictionRecord


class LRUCache(val cacheSize: Int) {

    private var map: LinkedHashMap<String, Bitmap> = LinkedHashMap(0, 0.75f, true)
    private var currentSize = 0
    private var hitUrls = 0
    private var missUrls = 0;
    private var deletedUrls = 0

    private val evictionHistory = ArrayDeque<EvictionRecord>(10)

    @Synchronized
    fun addUrlEntry(url: String, bitmap: Bitmap, sequenceNumber: Int) {

        if (bitmap.byteCount > cacheSize)
            return

        while (currentSize + bitmap.byteCount > cacheSize) {
            
            deleteUrlEntry(sequenceNumber)
        }
        map[url] = bitmap
        currentSize += bitmap.byteCount

    }

    @Synchronized
    fun getBitmap(url: String): Bitmap? {
        val bitmap = map[url]
        if (bitmap != null) {
            hitUrls++    // cache hit
        } else {
            missUrls++   // cache miss
        }
        return bitmap
    }

    @Synchronized
    fun deleteUrlEntry(sequenceNumber:Int) {

        val firstEntry = map.entries.iterator().next()
        currentSize -= firstEntry.value.byteCount
        deletedUrls++
        evictionHistory.addLast(
            EvictionRecord(
                url = firstEntry.key,
                sequenceNumber = sequenceNumber,
                sizeBytes = firstEntry.value.byteCount
            )
        )
        map.remove(firstEntry.key)

    }

    @Synchronized
    fun clear() {
        map.clear()
        hitUrls=0
        missUrls=0
        evictionHistory.clear()
        currentSize = 0
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
            recentEvictions = evictionHistory
        )

    }
}