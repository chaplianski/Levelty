package com.example.levelty.presenter.utils

import android.util.Log
import com.example.levelty.data.storage.model.KidProcessedTaskDTO
import com.example.levelty.domain.models.AssignedTasksItem
import com.example.levelty.domain.models.EditTask
import com.example.levelty.domain.models.KidProcessedTask
import com.example.levelty.domain.models.ParentProcessedTask

fun ParentProcessedTask.mapToEditTask (): EditTask {
    return EditTask(
        id = id,
        title = title,
        parentPurpose = parentPurpose,
        cost = cost,
        description = description,
        createdAt = createdAt,
        assigneeId = assigneeId,
        isPeriodic = isPeriodic,
        status = status,
        repeatInterval = repeatInterval,
        choreDate = choreDate
    )
}
//TODO refactor)
fun assignedTasksItemToProcessedTask(assignedTasks: List<AssignedTasksItem?>?): List<KidProcessedTask>{

    val processedTasks = mutableListOf<KidProcessedTask>()
    if (assignedTasks != null) {
        for (task in assignedTasks){
            if (task?.chores != null){
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
                        KidProcessedTask(id, title, parentPurpose, cost, description, createdAt,
                            assigneeId, categoryId, category, creatorId, isPeriodic, repeatInterval, childInterests, status, choreDate,
                            choreFinishDate, choreStatus, choreID, choreComment )
                    )
                }
            }
        }
    }
    return processedTasks
}