package com.example.levelty.domain.usecases

import com.example.levelty.domain.models.Repeat
import com.example.levelty.domain.repository.RepeatRepository
import javax.inject.Inject

class GetRepeatVariantsUseCase @Inject constructor(private val repeatRepository: RepeatRepository) {

    fun execute(): List<Repeat>{
        return repeatRepository.getRepeatVariants()
    }
}