package com.example.imagefeedapp.data.loader

import android.graphics.BitmapFactory
import android.os.SystemClock
import com.example.imagefeedapp.data.cache.LRUCache
import com.example.imagefeedapp.domain.model.BitmapResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
            return BitmapResult(cached,true,"<1ms")
        } else {

            return withContext(Dispatchers.IO) {
                val startTime = SystemClock.elapsedRealtime()
                val request = Request.Builder().url(url).build()
                val response = okHttpClient.newCall(request).execute()
                response.body.use { body ->
                    val bitmap = BitmapFactory.decodeStream(body.byteStream())
                    if (bitmap != null) {
                        lruImageCache.addUrlEntry(url, bitmap)
                        val elapsedMillis = SystemClock.elapsedRealtime() - startTime;

                        BitmapResult(bitmap,false,"~${elapsedMillis}ms")
                    } else {
                        null
                    }

                }
            }
        }
    }
}