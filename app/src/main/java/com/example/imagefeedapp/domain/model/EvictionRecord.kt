package com.example.imagefeedapp.domain.model

data class EvictionRecord(
    val url: String,
    val sizeBytes: Int,
    val timestamp: Long = System.currentTimeMillis()
)