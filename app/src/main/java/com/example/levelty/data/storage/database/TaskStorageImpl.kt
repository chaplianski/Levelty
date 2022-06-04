package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.TaskDTO
import com.example.levelty.data.storage.storage.TaskStorage
import com.example.levelty.domain.models.Task
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

class TaskStorageImpl @Inject constructor(): TaskStorage {

    override fun getDayTask(): List<TaskDTO> {
        val taskList = mutableListOf<TaskDTO>()
        val dateToday = Calendar.getInstance().timeInMillis
        val startDate = Calendar.getInstance().timeInMillis
        taskList.add(TaskDTO(0, "Find key", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", 0L))
        taskList.add(TaskDTO(1, "Walk dog", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", 1L))
        return taskList.toList()
    }
}