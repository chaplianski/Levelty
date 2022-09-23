package com.example.levelty.domain.usecases.kid

import com.example.levelty.domain.repository.GoalRepository
import javax.inject.Inject

class CompleteGoalUseCase @Inject constructor(private val goalRepository: GoalRepository) {

    suspend fun execute(goalId: Int){
        goalRepository.completeGoal(goalId)
    }
}