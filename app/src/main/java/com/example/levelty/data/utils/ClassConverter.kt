package com.example.levelty.data.repository

import android.util.Log
import com.example.levelty.data.storage.model.AssignedTasksItemDTO
import com.example.levelty.data.storage.model.CreatedTasksItemDTO
import com.example.levelty.data.storage.model.KidProcessedTaskDTO
import com.example.levelty.data.storage.model.ParentProcessedTaskDTO
import com.example.levelty.domain.models.*


fun createdTasksItemToProcessedTask(createdTasks: List<CreatedTasksItemDTO>): List<ParentProcessedTaskDTO>{

    val processedTasks = mutableListOf<ParentProcessedTaskDTO>()
    for (task in createdTasks){
        if (task.chores != null){
            for (chore in task.chores){
                val id = task.id
                val title = task.title
                val parentPurpose = task.parentPurpose
                val cost = task.cost
                val description = task.description
                val createdAt = task.createdAt
                val assigneeId = task.assigneeId
                val categoryId = task.categoryId
                val category = task.category
                val creatorId = task.creatorId
                val isPeriodic = task.isPeriodic
                val repeatInterval = task.repeatInterval
                val childInterests = task.childInterests
                val status = task.status
                val choreDate = chore?.date
                val choreStatus = chore?.status
                val choreID = chore?.id
                val choreComment = chore?.comment
                val choreFinishDate = chore?.finishedDatetime
            processedTasks.add(
                ParentProcessedTaskDTO(id, title, parentPurpose, cost, description, createdAt,
                assigneeId, categoryId, category, creatorId, isPeriodic, repeatInterval, childInterests, status, choreDate,
                choreFinishDate, choreStatus, choreID, choreComment )
            )
            }
        }
    }
    return processedTasks
}

//fun CreatedTasksItemDTO.tasksMapDataToDomain(): List<ParentProcessedTaskDTO> {
//    return List<ParentProcessedTaskDTO>(
//        id = id,
//        title = title,
//        parentPurpose = parentPurpose,
//        cost = cost,
//        description = description,
//        createdAt = createdAt,
//        assigneeId = assigneeId,
//        categoryId = categoryId,
//        category = category,
//        creatorId = creatorId,
//        isPeriodic = isPeriodic,
//        repeatInterval = repeatInterval,
//        childInterests = childInterests,
//        status = status,
//        choreDate = chores.map {  },
//        choreFinishDate = chores,
//        choreStatus = chores,
//        choreID = chores,
//        choreComment = chores
//
//    )
//}


fun assignedTasksItemToProcessedTask(assignedTasks: List<AssignedTasksItemDTO>): List<KidProcessedTaskDTO>{

    val processedTasks = mutableListOf<KidProcessedTaskDTO>()
    for (task in assignedTasks){
        if (task.chores != null){
            for (chore in task.chores){
                val id = task.id
                val title = task.title
                val parentPurpose = task.parentPurpose
                val cost = task.cost
                val description = task.description
                val createdAt = task.createdAt
                val assigneeId = task.assigneeId
                val categoryId = task.categoryId
                val category = task.category
                val creatorId = task.creatorId
                val isPeriodic = task.isPeriodic
                val repeatInterval = task.repeatInterval
                val childInterests = task.childInterests
                val status = task.status
                val choreDate = chore?.date
                val choreStatus = chore?.status
                val choreID = chore?.id
                val choreComment = chore?.comment
                val choreFinishDate = chore?.finishedDatetime
                processedTasks.add(
                    KidProcessedTaskDTO(id, title, parentPurpose, cost, description, createdAt,
                        assigneeId, categoryId, category, creatorId, isPeriodic, repeatInterval, childInterests, status, choreDate,
                        choreFinishDate, choreStatus, choreID, choreComment )
                )
//                Log.d("MyLog", "task = $title")
            }
        }
    }
    return processedTasks
}
