package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.models.GoalsItem

interface GoalRepository {

    fun getKidsGoals(): List<GoalsItem>
}