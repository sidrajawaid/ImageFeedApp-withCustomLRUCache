package com.example.imagefeedapp.domain.model

data class CacheStats (
    val currentCacheSize: Int,
    val maxSize: Int,
    val hitCount: Int,
    val missCount: Int,
    val evictionCount: Int,
    val hitRate: Float
)