package com.example.levelty.domain.repository

import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.ProcessedTask
import com.example.levelty.domain.models.Task

interface TaskRepository {

    fun getTodayTasks(kidId: Int, date: String): List<CreatedTasksItem>

    fun getDayTasks(kidName: String, date: String): List<Task>

//    fun getTasksList(): List<CreatedTasksItem>
    fun getTasksList(): List<ProcessedTask>

    fun getKidDetailTasksList(): List<CreatedTasksItem>

    fun addTask(task: Task)

    fun updateTask(task: Task)
}