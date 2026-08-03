package com.example.imagefeedapp.data.loader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.imagefeedapp.data.cache.LRUCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class BitmapLoader @Inject constructor( private val okHttpClient: OkHttpClient,
    private val lruImageCache: LRUCache) {
    suspend fun loadBitmap(url: String): Bitmap? {
        val cached = lruImageCache.getBitmap(url)
        if (cached != null) {
            return cached
        } else {
            return withContext(Dispatchers.IO) {
                val request = Request.Builder().url(url).build()
                val response = okHttpClient.newCall(request).execute()
                response.body?.use { body ->
                    val bitmap = BitmapFactory.decodeStream(body.byteStream())
                    if (bitmap != null) {
                        lruImageCache.addUrlEntry(url, bitmap)
                        bitmap
                    } else {
                        null
                    }

                }
            }
        }
    }
}