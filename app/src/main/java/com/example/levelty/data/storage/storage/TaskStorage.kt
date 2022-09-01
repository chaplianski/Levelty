package com.example.levelty.data.storage.storage

import com.example.levelty.data.storage.model.CreatedTasksItemDTO
import com.example.levelty.data.storage.model.TaskDTO

interface TaskStorage {

    fun getDayTask(kidName: String, date: String): List<TaskDTO>

    fun getTodayTask(kidId: Int, date: String): List<CreatedTasksItemDTO>

    fun getTasksList(): List<CreatedTasksItemDTO>

    fun getKidDetailTasksList(): List<TaskDTO>

    fun addTask(taskDTO: TaskDTO)

    fun updateTask(taskDTO: TaskDTO)
}