package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.GoalDTO
import com.example.levelty.data.storage.storage.GoalStorage
import javax.inject.Inject

class GoalStorageImpl @Inject constructor(): GoalStorage {

    override fun getKidsGoals(): List<GoalDTO> {
        val goalList = mutableListOf<GoalDTO>()
        goalList.add(GoalDTO(0, "Health", 10, false, 1))
        goalList.add(GoalDTO(1, "Sport", 34, false, 2))
        goalList.add(GoalDTO(2, "Science", 48, false, 3))
        return goalList.toList()
    }
}