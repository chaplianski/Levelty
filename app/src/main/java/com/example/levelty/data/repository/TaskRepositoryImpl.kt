package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.TaskStorageImpl
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskStorageImpl: TaskStorageImpl): TaskRepository {

    override fun getUpcomingTasks(): List<Task> {
        return taskStorageImpl.getUncomingTask().map { it.taskMapDataToDomain() }
    }

    override fun getDayTasks(): List<Task> {
        return taskStorageImpl.getDayTask().map { it.taskMapDataToDomain() }
    }

    override fun getTasksList(): List<Task> {
        return taskStorageImpl.getTasksList().map { it.taskMapDataToDomain() }
    }

    override fun getKidDetailTasksList(): List<Task> {
        return taskStorageImpl.getKidDetailTasksList().map { it.taskMapDataToDomain() }
    }
}