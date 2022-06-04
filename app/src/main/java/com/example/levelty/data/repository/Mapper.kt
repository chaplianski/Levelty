package com.example.levelty.data.repository

import com.example.levelty.data.storage.model.TaskDTO
import com.example.levelty.domain.models.Task

class Mapper {
}

fun TaskDTO.taskMapDataToDomain(): Task {
    return Task(
    taskId = taskId,
    taskName = taskName,
    taskCategory = taskCategory,
    taskPoints = taskPoints,
    taskDate = taskDate,
    taskStartTime = taskStartTime,
    taskRepeat = taskRepeat,
    taskParentPurpose = taskParentPurpose,
    taskKidsInterest = taskKidsInterest,
    kidId = kidId
    )
}