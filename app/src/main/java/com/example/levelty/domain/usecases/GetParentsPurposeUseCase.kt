package com.example.levelty.domain.usecases

import com.example.levelty.domain.models.Purpose
import com.example.levelty.domain.repository.PurposeRepository
import javax.inject.Inject

class GetParentsPurposeUseCase @Inject constructor(private val purposeRepository: PurposeRepository) {

    fun execute(): List<Purpose>{
        return purposeRepository.getParentsPurpose()
    }
}