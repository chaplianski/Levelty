package com.example.levelty.presenter.utils

import com.example.levelty.domain.models.EditTask
import com.example.levelty.domain.models.ProcessedTask

fun ProcessedTask.mapToEditTask (): EditTask {
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