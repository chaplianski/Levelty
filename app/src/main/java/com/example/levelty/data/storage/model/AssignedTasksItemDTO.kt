package com.example.levelty.data.storage.model

data class AssignedTasksItemDTO(
    val cost: Int? = null,
    val dueDate: String? = null,
    val description: String? = null,
    val parentPurpose: String? = null,
    val createdAt: String? = null,
    val childInterests: List<ChildInterestsItemDTO?>? = null,
    val chores: List<ChoresItemDTO?>? = null,
    val title: String? = null,
    val categoryId: Int? = null,
    val creatorId: Int? = null,
    val repeatInterval: Int? = null,
    val id: Int? = null,
    val category: CategoryDTO? = null,
    val isPeriodic: Boolean? = null,
    val startDate: String? = null,
    val assigneeId: Int? = null,
    val status: String? = null
)