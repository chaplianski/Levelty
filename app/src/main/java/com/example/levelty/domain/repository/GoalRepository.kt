package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Goal

interface GoalRepository {

    fun getKidsGoals(): List<Goal>
}