package com.example.levelty.data.repository

import com.example.levelty.data.storage.model.CreatedTasksItemDTO
import com.example.levelty.data.storage.model.ProcessedTaskDTO
import com.example.levelty.domain.models.*


fun createdTasksItemToProcessedTask(createdTasks: List<CreatedTasksItemDTO>): List<ProcessedTaskDTO>{

    val processedTasks = mutableListOf<ProcessedTaskDTO>()
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
                val childInterests = task.childInterests
                val status = task.status
                val choreDate = chore?.date
                val choreStatus = chore?.status
                val choreID = chore?.id
                val choreComment = chore?.comment
                val choreFinishDate = chore?.finishedDatetime
            processedTasks.add(
                ProcessedTaskDTO(id, title, parentPurpose, cost, description, createdAt,
                assigneeId, categoryId, category, creatorId, childInterests, status, choreDate,
                choreFinishDate, choreStatus, choreID, choreComment )
            )
            }
        }
    }
    return processedTasks
}

