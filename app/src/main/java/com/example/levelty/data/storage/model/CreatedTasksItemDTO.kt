package com.example.levelty.data.storage.model

data class CreatedTasksItemDTO (
    val id: Int? = null,
    val title: String? = null,
    val parentPurpose: String? = null,
    val cost: Int? = null,
    val isPeriodic: Boolean? = null,
    val repeatInterval: Int? = null,
    val dueDate: String? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val childInterests: List<ChildInterestsItemDTO?>? = null,
    val chores: List<ChoresItemDTO?>? = null,
    val categoryId: Int? = null,
    val creatorId: Int? = null,
    val category: CategoryDTO? = null,
    val startDate: String? = null,
    val assigneeId: Int? = null,
    val status: String? = null
        )