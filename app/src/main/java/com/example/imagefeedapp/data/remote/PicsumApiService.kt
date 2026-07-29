package com.example.imagefeedapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface PicsumApiService {
    @GET("id/{id}/info")
    suspend fun getImageInfo(@Path("id") id: Int): ImageDto
}