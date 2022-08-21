package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.Kid
import com.example.levelty.domain.repository.KidRepository
import javax.inject.Inject

class GetKidUseCase @Inject constructor(private val kidRepository: KidRepository) {

    fun execute (kidId: Long): Kid {
        return kidRepository.getKid(kidId)
    }
}