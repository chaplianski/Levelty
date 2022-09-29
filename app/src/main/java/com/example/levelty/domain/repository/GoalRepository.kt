package com.example.levelty.domain.repository

import com.example.levelty.domain.models.GoalsItem

interface GoalRepository {
//TODO по наименованию for example getKidGoalList
    fun getKidsGoals(): List<GoalsItem>

    suspend fun completeGoal(goalId: Int)

    suspend fun approveGoal(goalId: Int, goalPrice: Int): GoalsItem
}