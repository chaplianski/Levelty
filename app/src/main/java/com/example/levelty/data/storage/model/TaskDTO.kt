package com.example.levelty.data.storage.model

data class TaskDTO(
    val taskId: Long,
    val taskName: String,
    val taskCategory: String,
    val taskPoints: Int,
    val taskDate: Long,
    val taskStartTime: Long,
    val taskRepeat: String,
    val taskParentPurpose: String,
    val taskKidsInterest: String,
    val kidId: Long

)
