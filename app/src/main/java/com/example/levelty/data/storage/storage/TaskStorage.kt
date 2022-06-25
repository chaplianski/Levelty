package com.example.levelty.data.storage.storage

import com.example.levelty.data.storage.model.TaskDTO

interface TaskStorage {

    fun getDayTask(kidName: String, date: String): List<TaskDTO>

    fun getTodayTask(kidName: String, date: String): List<TaskDTO>

    fun getTasksList(): List<TaskDTO>

    fun getKidDetailTasksList(): List<TaskDTO>

    fun addTask(taskDTO: TaskDTO)
}