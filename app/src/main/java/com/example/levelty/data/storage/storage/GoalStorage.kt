package com.example.levelty.data.storage.storage

import com.example.levelty.data.storage.model.GoalsItemDTO

interface GoalStorage {

    fun getKidsGoals(): List<GoalsItemDTO>
}