package com.example.imagefeedapp.domain.model

import android.graphics.Bitmap

data class BitmapResult(
    val bitmap: Bitmap?,
    val isFromCache: Boolean
)