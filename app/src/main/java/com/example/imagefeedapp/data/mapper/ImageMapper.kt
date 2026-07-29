package com.example.imagefeedapp.data.mapper

import com.example.imagefeedapp.data.remote.ImageDto
import com.example.imagefeedapp.domain.model.ImageModel


fun ImageDto.toImageModel(): ImageModel {
        return ImageModel(
            id = this.id,
            downloadUrl = this.downloadUrl,
            width = this.width,
            height= this.height,
            url =this.url

        )
    }