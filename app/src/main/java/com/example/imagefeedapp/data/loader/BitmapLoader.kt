package com.example.imagefeedapp.data.loader

import com.example.imagefeedapp.data.cache.LRUCache
import okhttp3.OkHttpClient
import javax.inject.Inject

class BitmapLoader @Inject constructor( private val okHttpClient: OkHttpClient,
    private val lruCache: LRUCache) {
}