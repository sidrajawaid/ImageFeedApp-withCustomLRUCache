package com.example.imagefeedapp.domain.model

data class ImageModel(
    val id:String,
    val url:String,
    val width:Int,
    val height:Int,
    val downloadUrl:String,
 )