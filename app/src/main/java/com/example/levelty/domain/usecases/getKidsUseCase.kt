package com.example.levelty.domain.usecases

import com.example.levelty.domain.Kid
import com.example.levelty.domain.repository.KidRepository
import javax.inject.Inject

class getKidsUseCase @Inject constructor(private val kidRepository: KidRepository){

    fun execute(): List<Kid> {
        return kidRepository.getKids()
    }
}