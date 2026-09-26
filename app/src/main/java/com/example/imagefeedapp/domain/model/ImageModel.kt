package com.example.imagefeedapp.domain.model

data class ImageModel(
    val id:String,
  //  val urls: UrlsDto,
    val width:Int,
    val height:Int,
    val smallImageUrl:String,
    val regularImageUrl:String=""
 )