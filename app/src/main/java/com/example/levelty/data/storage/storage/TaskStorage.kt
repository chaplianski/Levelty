package com.example.levelty.data.storage.storage

import com.example.levelty.data.storage.model.TaskDTO

interface TaskStorage {

    fun getDayTask(): List<TaskDTO>
}