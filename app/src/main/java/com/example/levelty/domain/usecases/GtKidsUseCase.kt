package com.example.levelty.domain.usecases

import com.example.levelty.domain.models.Kid
import com.example.levelty.domain.repository.KidRepository
import javax.inject.Inject

class GtKidsUseCase @Inject constructor(private val kidRepository: KidRepository){

    fun execute(): List<Kid> {
        return kidRepository.getKids()
    }
}