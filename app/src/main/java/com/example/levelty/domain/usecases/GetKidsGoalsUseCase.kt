package com.example.levelty.domain.usecases

import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.repository.GoalRepository
import javax.inject.Inject

class GetKidsGoalsUseCase @Inject constructor(private val goalRepository: GoalRepository) {

    fun execute (): List<Goal>{
        return goalRepository.getKidsGoals()
    }
}