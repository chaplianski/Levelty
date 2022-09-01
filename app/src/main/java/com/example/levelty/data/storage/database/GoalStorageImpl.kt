package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.GoalDTO
import com.example.levelty.data.storage.model.GoalsItemDTO
import com.example.levelty.data.storage.storage.GoalStorage
import com.example.levelty.domain.models.GoalsItem
import javax.inject.Inject

class GoalStorageImpl @Inject constructor(): GoalStorage {

    override fun getKidsGoals(): List<GoalsItemDTO> {
        val goalList = mutableListOf<GoalsItemDTO>()
        goalList.add(GoalsItemDTO(0, 0, 250, "New phone", "March 12 2022", "Phone", "need approval" ))
        goalList.add(GoalsItemDTO(0, 1, 350, "New bike", "June 22 2022", "Bike", "need approval" ))
        goalList.add(GoalsItemDTO(0, 0, 200, "New trust", "June 10 2022", "Trust", "need approval" ))
        goalList.add(GoalsItemDTO(0, 0, 550, "Upgrade computer", "August 04 2022", "Computer", "need approval" ))
        goalList.add(GoalsItemDTO(0, 1, 150, "New drill", "March 27 2022", "Drill", "need approval" ))
        return goalList.toList()
    }
}