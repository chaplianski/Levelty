package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Task

interface TaskRepository {

    fun getTodayTasks(kidName: String, date: String): List<Task>

    fun getDayTasks(kidName: String, date: String): List<Task>

    fun getTasksList(): List<Task>

    fun getKidDetailTasksList(): List<Task>

    fun addTask(task: Task)
}