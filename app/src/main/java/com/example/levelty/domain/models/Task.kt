package com.example.levelty.domain.models

data class Task(
    val taskId: Long,
    val taskName: String,
    val taskCategory: String,
    val taskPoints: Int,
    val taskDate: String,
    val taskStartTime: String,
    val taskRepeat: String,
    val taskParentPurpose: String,
    val taskKidsInterest: String,
    val kidName: String,
    val taskStatus: String
)
