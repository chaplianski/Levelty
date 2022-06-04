package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Task

interface TaskRepository {

    fun getIncomingTasks(): List<Task>

    fun getDayTasks(): List<Task>
}