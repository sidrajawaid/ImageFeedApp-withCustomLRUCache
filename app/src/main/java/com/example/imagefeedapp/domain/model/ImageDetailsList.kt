package com.example.imagefeedapp.domain.model

data class ImageDetailModel(
    val imageDetailLabel:List<DetailLabel>,
    val imageDetailValue:List<DetailValue>
)


data class DetailLabel(val imageId:String,
                       val dimensions:String,
                       val cacheSize:String,
                       val loadTime:String)

data class DetailValue(val imageIdValue:String,
                       val dimensionsValue:String,
                       val cacheSizeValue:String,
                       val loadTimeValue:String)