package com.example.levelty.data.repository

import android.util.Log
import com.example.levelty.data.network.retrofit.kid.UpdateChoreStatusApiHelper
import com.example.levelty.data.network.retrofit.parents.CreateNewTaskApiHelper
import com.example.levelty.data.network.retrofit.parents.UpdateParenTaskApiHelper
import com.example.levelty.data.network.retrofit.parents.UpdateTaskStatusApiHelper
import com.example.levelty.data.storage.database.TaskStorageImpl
import com.example.levelty.domain.models.*
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskStorageImpl: TaskStorageImpl,
    private val updateTaskStatusApiHelper: UpdateTaskStatusApiHelper,
    private val createNewTaskApiHelper: CreateNewTaskApiHelper,
    private val updateParenTaskApiHelper: UpdateParenTaskApiHelper,
    private val updateChoreStatusApiHelper: UpdateChoreStatusApiHelper
) :
    TaskRepository {

    override fun getTodayTasks(kidId: Int, date: String): List<CreatedTasksItem> {
        val list = taskStorageImpl.getTodayTask(kidId, date).map { it.taskMapDataToDomain() }
//        Log.d("MyLog", "list in taskRepositoryImpl = $list")
        return list
    }

//    override fun getDayTasks(kidName: String, date: String): List<Task> {
//        return taskStorageImpl.getDayTask(kidName, date).map { it.taskMapDataToDomain() }
//    }

    //    override fun getTasksList(): List<CreatedTasksItem> {
//        return taskStorageImpl.getTasksList().map { it.taskMapDataToDomain() }
//    }
    override fun getTasksList(): List<ParentProcessedTask> {
        val createdTasks = taskStorageImpl.getCreatedTasksList()
        return createdTasksItemToProcessedTask(createdTasks).map { it.processedMapTaskDataToDomain() }
    }

    override suspend fun updateChoreStatus(choreId: Int) {
//        updateChoreStatusApiHelper.updateChoreStatus(choreId)
        Log.d("MyLog", "Update chore Id to server, choreId = $choreId")
    }


    override fun getKidTasksList(): List<KidProcessedTask> {
        val assignedTasks = taskStorageImpl.getAssignedTaskList()
//        Log.d("MyLog", "list in taskRepositoryImpl = ${assignedTasksItemToProcessedTask(assignedTasks).map { it.kidProcessedTaskMapDataToDomain() }}")
        return assignedTasksItemToProcessedTask(assignedTasks).map { it.kidProcessedTaskMapDataToDomain() }
    }



    override suspend fun addTask(newTask: NewTask) {
        createNewTaskApiHelper.createNewTask(newTask.taskMapDomainToData())
    }

    override suspend fun updateTaskStatus(taskId: Int, status: String) {
        updateTaskStatusApiHelper.updateTask(taskId, status)
    }

    override suspend fun updateParentTask(taskId: Int, newTask: NewTask) {
        updateParenTaskApiHelper.updateTask(taskId, newTask.taskMapDomainToData())
    }

    override fun getDataVariants(): List<String> {
        return listOf ("Today", "Tomorrow", "Set another day")
    }

    override fun getPointsVariants(): List<String> {
        return listOf ("5", "15", "35", "55", "80", "Custom")
    }

    override fun getRepeatVariants(): List<String> {
        return listOf("Don't repeat", "Daily", "Every 3 days", "Every week", "Every month", "Custom")
    }


}