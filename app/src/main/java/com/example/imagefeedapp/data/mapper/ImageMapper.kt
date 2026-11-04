package com.example.imagefeedapp.data.mapper

import com.example.imagefeedapp.data.remote.ImageDto
import com.example.imagefeedapp.domain.model.ImageModel


fun ImageDto.toImageModel(seqNum: Int): ImageModel {

        return ImageModel(
            id = this.id,
            sequenceNumber=seqNum,
            width = this.width,
            height= this.height,
            smallImageUrl =this.urls.small,
            regularImageUrl = this.urls.regular
        )
    }