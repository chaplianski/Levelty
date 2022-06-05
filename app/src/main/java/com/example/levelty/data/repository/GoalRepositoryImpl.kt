package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.GoalStorageImpl
import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.repository.GoalRepository
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(private val goalStorageImpl: GoalStorageImpl): GoalRepository {

    override fun getKidsGoals(): List<Goal> {
        return goalStorageImpl.getKidsGoals().map { it.goalMapDataToDomain() }
    }
}