package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.Purpose
import com.example.levelty.domain.repository.PurposeRepository
import javax.inject.Inject

class GetParentPurposesUseCase @Inject constructor(private val purposeRepository: PurposeRepository) {

    fun execute(): List<String> {
        return purposeRepository.getParentsPurposes()
    }
}