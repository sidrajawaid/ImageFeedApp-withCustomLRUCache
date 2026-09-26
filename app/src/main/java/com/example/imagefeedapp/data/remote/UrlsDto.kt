package com.example.imagefeedapp.data.remote

import com.google.gson.annotations.SerializedName

data class UrlsDto ( @SerializedName("small") val small:String,
                     @SerializedName("regular") val regular:String)