package com.example.levelty.data.repository

import android.util.Log
import com.example.levelty.data.storage.database.TaskStorageImpl
import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.ProcessedTask
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskStorageImpl: TaskStorageImpl) :
    TaskRepository {

    override fun getTodayTasks(kidId: Int, date: String): List<CreatedTasksItem> {
        val list = taskStorageImpl.getTodayTask(kidId, date).map { it.taskMapDataToDomain() }
//        Log.d("MyLog", "list in taskRepositoryImpl = $list")
        return list
    }

    override fun getDayTasks(kidName: String, date: String): List<Task> {
        return taskStorageImpl.getDayTask(kidName, date).map { it.taskMapDataToDomain() }
    }

    //    override fun getTasksList(): List<CreatedTasksItem> {
//        return taskStorageImpl.getTasksList().map { it.taskMapDataToDomain() }
//    }
    override fun getTasksList(): List<ProcessedTask> {
        val createdTasks = taskStorageImpl.getTasksList()
        return createdTasksItemToProcessedTask(createdTasks).map { it.processedTaskDataToDomain() }
    }


    override fun getKidDetailTasksList(): List<CreatedTasksItem> {
        return taskStorageImpl.getTasksList().map { it.taskMapDataToDomain() }
    }

    override fun addTask(task: Task) {
        val newTask = task.taskMapDomainToData()
        taskStorageImpl.addTask(newTask)
    }

    override fun updateTask(task: Task) {
        taskStorageImpl.updateTask(task.taskMapDomainToData())
    }
}