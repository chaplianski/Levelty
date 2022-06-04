package com.example.levelty.data.repository

import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.repository.GoalRepository
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(): GoalRepository {
    override fun getKidsGoals(): List<Goal> {
        TODO("Not yet implemented")
    }
}