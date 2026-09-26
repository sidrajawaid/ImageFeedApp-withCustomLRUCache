package com.example.imagefeedapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("/photos")
    suspend fun getImageInformation( @Query ("page") page: Int ,
                                      @Query ("per_page") perPage: Int ) : List<ImageDto>
}