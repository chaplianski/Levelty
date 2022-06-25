package com.example.levelty.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskDTO(
    @PrimaryKey(autoGenerate = true)
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
