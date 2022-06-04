package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.TaskStorageImpl
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskStorageImpl: TaskStorageImpl): TaskRepository {

    override fun getIncomingTasks(): List<Task> {
        return taskStorageImpl.getDayTask().map { it.taskMapDataToDomain() }
    }

    override fun getDayTasks(): List<Task> {
        return taskStorageImpl.getDayTask().map { it.taskMapDataToDomain() }
    }
}