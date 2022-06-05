package com.example.levelty.data.storage.storage

import com.example.levelty.data.storage.model.GoalDTO

interface GoalStorage {

    fun getKidsGoals(): List<GoalDTO>
}