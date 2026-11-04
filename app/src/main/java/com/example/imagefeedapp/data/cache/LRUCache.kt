package com.example.imagefeedapp.data.cache

import android.graphics.Bitmap
import com.example.imagefeedapp.domain.model.CacheStats
import com.example.imagefeedapp.domain.model.EvictionRecord


class LRUCache(val cacheSize:Int) {

   private var map: LinkedHashMap<String, Bitmap> = LinkedHashMap(0,0.75f,true)
    private var currentSize = 0
    private var hitUrls = 0
    private var missUrls = 0;
    private var deletedUrls = 0

    private val evictionHistory = ArrayDeque<EvictionRecord>(10)
    @Synchronized
    fun addUrlEntry(url: String, bitmap: Bitmap, label: String = "") {

        if (bitmap.byteCount > cacheSize)
            return

        while (currentSize + bitmap.byteCount > cacheSize) {
            deleteUrlEntry()
        }
        map[url] = bitmap
        currentSize += bitmap.byteCount

    }

    @Synchronized
    fun getBitmap(url: String): Bitmap? {
        val bitmap=map[url]
        if (bitmap != null) {
            hitUrls++    // cache hit
        } else {
            missUrls++   // cache miss
        }
        return bitmap
    }
    @Synchronized
    fun deleteUrlEntry() {

        val firstEntry = map.entries.iterator().next()
        map.remove(firstEntry.key)
        currentSize -= firstEntry.value.byteCount
        deletedUrls++
        evictionHistory.addLast(
            EvictionRecord(
                url = firstEntry.key,
                sequenceNumber=getIndexFromKey(firstEntry.key),
                sizeBytes = firstEntry.value.byteCount
            )
        )

    }
    fun  getIndexFromKey( key: String): Int {
        if (key == null) {
            return -1 // Invalid input
        }

        var index = 0
        for (currentKey in map.keys) {
            if (currentKey == key) {
                return index
            }
            index++
        }
        return -1 // Key not found
    }

    @Synchronized
    fun clear() {
        map.clear()
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
            recentEvictions=evictionHistory
        )

    }
}