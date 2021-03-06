package com.example.levelty.data.repository

import com.example.levelty.data.storage.model.*
import com.example.levelty.domain.models.*

//class Mapper {
//}

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
    kidName = kidName,
    taskStatus = taskStatus)
}

fun Task.taskMapDomainToData(): TaskDTO {
    return TaskDTO(
        taskId = taskId,
        taskName = taskName,
        taskCategory = taskCategory,
        taskPoints = taskPoints,
        taskDate = taskDate,
        taskStartTime = taskStartTime,
        taskRepeat = taskRepeat,
        taskParentPurpose = taskParentPurpose,
        taskKidsInterest = taskKidsInterest,
        kidName = kidName,
        taskStatus = taskStatus)
}

fun PurposeDTO.purposeMapDataToDomain(): Purpose {
    return Purpose(
        purposeId = purposeId,
        purposeName = purposeName
    )
}

fun InterestDTO.interestMapDataToDomain(): Interest {
    return Interest(
        interestId = interestId,
        interestName = interestName
    )
}

fun KidDTO.kidMapDataToDomain(): Kid {
    return Kid(
        kidId = kidId,
        kidName = kidName
    )
}

fun GoalDTO.goalMapDataToDomain(): Goal {
    return Goal(
        goalId = goalId,
        goalName = goalName,
        goalValue = goalValue,
        isApproval = isApproval,
        kidId = kidId
    )
}

