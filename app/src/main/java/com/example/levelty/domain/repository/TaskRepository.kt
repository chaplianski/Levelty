package com.example.levelty.domain.repository

import com.example.levelty.domain.models.*

interface TaskRepository {

    fun getTodayTasks(kidId: Int, date: String): List<CreatedTasksItem>

//    fun getDayTasks(kidName: String, date: String): List<Task>

    fun getKidTasksList(): List<KidProcessedTask>

//    fun getTasksList(): List<CreatedTasksItem>
    fun getTasksList(): List<ParentProcessedTask>

    suspend fun updateChoreStatus(choreId: Int)

    suspend fun addTask(newTask: NewTask)

    suspend fun updateTaskStatus(taskId: Int, status: String)

    suspend fun updateParentTask(taskId: Int, newTask: NewTask)

    fun getDataVariants(): List<String>

    fun getPointsVariants(): List<String>

    fun getRepeatVariants(): List <String>
}