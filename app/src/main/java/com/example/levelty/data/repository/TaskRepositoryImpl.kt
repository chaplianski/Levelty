package com.example.levelty.data.repository

import android.util.Log
import com.example.levelty.data.storage.database.TaskStorageImpl
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskStorageImpl: TaskStorageImpl): TaskRepository {

    override fun getTodayTasks(kidName: String, date: String): List<Task> {
        val list = taskStorageImpl.getTodayTask(kidName, date).map { it.taskMapDataToDomain() }
//        Log.d("MyLog", "list in taskRepositoryImpl = $list")
        return list
    }

    override fun getDayTasks(kidName: String, date: String): List<Task> {
        return taskStorageImpl.getDayTask(kidName, date).map { it.taskMapDataToDomain() }
    }

    override fun getTasksList(): List<Task> {
        return taskStorageImpl.getTasksList().map { it.taskMapDataToDomain() }
    }

    override fun getKidDetailTasksList(): List<Task> {
        return taskStorageImpl.getKidDetailTasksList().map { it.taskMapDataToDomain() }
    }

    override fun addTask(task: Task) {
        val newTask = task.taskMapDomainToData()
        taskStorageImpl.addTask(newTask)
    }

    override fun updateTask(task: Task) {
        taskStorageImpl.updateTask(task.taskMapDomainToData())
    }
}