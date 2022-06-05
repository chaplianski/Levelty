package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.GoalDTO
import com.example.levelty.data.storage.storage.GoalStorage
import javax.inject.Inject

class GoalStorageImpl @Inject constructor(): GoalStorage {

    override fun getKidsGoals(): List<GoalDTO> {
        val goalList = mutableListOf<GoalDTO>()
        goalList.add(GoalDTO(0, "Health", "Today", false, ))
        goalList.add(GoalDTO(1, "Sport", "Weekly", false, ))
        goalList.add(GoalDTO(2, "Science", "Weekly", false, ))
        return goalList.toList()
    }
}