package com.example.imagefeedapp.di

import com.example.imagefeedapp.data.cache.LRUCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLruImageCache(): LRUCache {
        val maxMemory = Runtime.getRuntime().maxMemory()
        val cacheSize = (maxMemory / 8).toInt()
        return LRUCache(cacheSize)
    }
}
