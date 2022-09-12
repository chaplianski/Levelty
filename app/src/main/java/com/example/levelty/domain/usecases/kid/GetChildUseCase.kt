package com.example.levelty.domain.usecases.kid

import com.example.levelty.domain.models.Child
import com.example.levelty.domain.repository.KidRepository
import javax.inject.Inject

class GetChildUseCase @Inject constructor(private val kidsRepository: KidRepository) {

    fun execute(): Child {
        return kidsRepository.getChild()
    }
}