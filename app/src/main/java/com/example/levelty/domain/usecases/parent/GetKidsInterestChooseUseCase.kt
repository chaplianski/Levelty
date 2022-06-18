package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.Interest
import com.example.levelty.domain.repository.InterestRepository
import javax.inject.Inject

class GetKidsInterestChooseUseCase @Inject constructor(private val interestRepository: InterestRepository) {

    fun execute(): List <Interest>{
        return interestRepository.getKidsInterestChoose()
    }

}