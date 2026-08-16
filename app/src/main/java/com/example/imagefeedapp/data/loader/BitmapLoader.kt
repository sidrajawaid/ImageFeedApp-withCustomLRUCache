package com.example.imagefeedapp.data.loader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.imagefeedapp.data.cache.LRUCache
import com.example.imagefeedapp.domain.model.BitmapResult
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
    suspend fun loadBitmap(url: String): BitmapResult? {
        val cached = lruImageCache.getBitmap(url)
        if (cached != null) {
            return BitmapResult(cached,true)
        } else {
            return withContext(Dispatchers.IO) {
                val request = Request.Builder().url(url).build()
                val response = okHttpClient.newCall(request).execute()
                response.body?.use { body ->
                    val bitmap = BitmapFactory.decodeStream(body.byteStream())
                    if (bitmap != null) {
                        lruImageCache.addUrlEntry(url, bitmap)
                        BitmapResult(bitmap,false)
                    } else {
                        null
                    }

                }
            }
        }
    }
}