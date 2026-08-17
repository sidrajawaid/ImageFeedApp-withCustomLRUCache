package com.example.imagefeedapp.data.cache

import android.graphics.Bitmap
import android.util.Log
import com.example.imagefeedapp.domain.model.CacheStats


class LRUCache(val cacheSize:Int) {

   private var map: LinkedHashMap<String, Bitmap> = LinkedHashMap(0,0.75f,true)
    private var currentSize = 0
    private var hitUrls = 0
    private var missUrls = 0;
    private var deletedUrls = 0
    @Synchronized
    fun addUrlEntry(url: String, bitmap: Bitmap) {

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
        Log.d("LRUCache", "Evicting: ${firstEntry.key}")
        map.remove(firstEntry.key)
        currentSize -= firstEntry.value.byteCount
        deletedUrls++
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
            hitRate = hitRate
        )

    }
}