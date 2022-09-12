package com.example.levelty.data.storage.model

data class NewTaskDTO(
    val childInterestIds: List<Int?>? = null,
    val cost: Int? = null,
    val categoryId: Int? = null,
    val dueDate: String? = null,
    val description: String? = null,
    val parentPurpose: String? = null,
    val customSchedule: List<String?>? = null,
    val repeatInterval: Int? = null,
    val title: String? = null,
    val isPeriodic: Boolean? = null,
    val startDate: String? = null,
    val assigneeId: Int? = null
)