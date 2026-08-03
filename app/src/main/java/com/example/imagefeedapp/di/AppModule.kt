package com.example.imagefeedapp.di

import android.content.Context
import com.example.imagefeedapp.data.cache.LRUCache
import com.example.imagefeedapp.data.loader.BitmapLoader
import com.example.imagefeedapp.data.remote.PicsumApiService
import com.example.imagefeedapp.data.network.NetworkMonitor
import com.example.imagefeedapp.data.repository.ImageRepositoryImpl
import com.example.imagefeedapp.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor {
        return NetworkMonitor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePicsumApiService(retrofit: Retrofit): PicsumApiService {
        return retrofit.create(PicsumApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(impl: ImageRepositoryImpl): ImageRepository {
        return impl
        }

}
