package com.example.imagefeedapp.data.remote

import com.example.imagefeedapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class UnsplashAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest= originalRequest.newBuilder()
        val requestBuilder=   newRequest.addHeader("Authorization","Client-ID ${BuildConfig.UNSPLASH_ACCESS_KEY}")
            .build()
     return   chain.proceed(requestBuilder)
    }
}