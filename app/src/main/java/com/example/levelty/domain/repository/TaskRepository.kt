package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Task

interface TaskRepository {

    fun getUpcomingTasks(): List<Task>

    fun getDayTasks(): List<Task>

    fun getTasksList(): List<Task>
}