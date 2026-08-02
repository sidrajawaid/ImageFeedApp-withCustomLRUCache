package com.example.imagefeedapp.domain.usecase

import com.example.imagefeedapp.domain.repository.ImageRepository
import javax.inject.Inject

class GetImagesUseCase @Inject constructor( private val imageRepository: ImageRepository){

    operator fun invoke() = imageRepository.getImages()


}