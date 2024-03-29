package com.example.levelty.data.repository

import com.example.levelty.data.network.retrofit.kid.CompleteGoalApiHelper
import com.example.levelty.data.network.retrofit.parents.ReviewChildGoalApiHelper
import com.example.levelty.data.storage.database.GoalStorageImpl
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.domain.repository.GoalRepository
import javax.inject.Inject

class GoalRepositoryImpl @Inject constructor(
    private val goalStorageImpl: GoalStorageImpl,
    private val completeGoalApiHelper: CompleteGoalApiHelper,
    private val reviewChildGoalApiHelper: ReviewChildGoalApiHelper
    ): GoalRepository {

    override fun getKidsGoals(): List<GoalsItem> {
        return goalStorageImpl.getKidsGoals().map { it.goalMapDataToDomain() }
    }

    override suspend fun completeGoal(goalId: Int) {
//        completeGoalApiHelper.goalComplete(goalId)
    }

    override suspend fun approveGoal(goalId: Int, goalPrice: Int): GoalsItem {
        return reviewChildGoalApiHelper.parentApproveGoal(goalId, goalPrice).goalMapDataToDomain()

    }
}