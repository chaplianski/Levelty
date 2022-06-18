package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.repository.GoalRepository
import javax.inject.Inject

class GetDayKidGoalsUseCase @Inject constructor(private val goalRepository: GoalRepository) {

    fun execute(): List<Goal>{
        return goalRepository.getKidsGoals()
    }
}