package com.example.imagefeedapp.data.remote

import com.google.gson.annotations.SerializedName

data class ImageDto(@SerializedName("id") val id:String,
                   val  sequenceNumber: Int,
                    @SerializedName("height") val height:Int,
                    @SerializedName("width") val width: Int,
                    @SerializedName("urls") val  urls: UrlsDto,

   )