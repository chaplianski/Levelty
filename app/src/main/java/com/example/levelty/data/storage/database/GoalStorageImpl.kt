package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.GoalsItemDTO
import com.example.levelty.data.storage.storage.GoalStorage
import javax.inject.Inject

class GoalStorageImpl @Inject constructor(): GoalStorage {

    override fun getKidsGoals(): List<GoalsItemDTO> {
        val goalList = mutableListOf<GoalsItemDTO>()
        goalList.add(GoalsItemDTO(0, 150, "150", "0", 0, "New version Iphone", "normal" ))
        goalList.add(GoalsItemDTO(0, 250, "", "New phone", 0, "Phone", "declined" ))
        goalList.add(GoalsItemDTO(0, 400, "", "New bike", 0, "Bike", "waiting_for_approval" ))
        goalList.add(GoalsItemDTO(0, 8000, "", "New trust", 0, "Car", "completed" ))
        goalList.add(GoalsItemDTO(0, 550, "", "Upgrade computer", 0, "Computer", "locked" ))
        goalList.add(GoalsItemDTO(0, 120, "", "New drill", 0, "Drill", "choose_goal_status" ))
        return goalList.toList()
    }
}