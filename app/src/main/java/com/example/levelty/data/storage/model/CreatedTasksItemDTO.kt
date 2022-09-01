package com.example.levelty.data.storage.model

import com.example.levelty.domain.models.Category
import com.example.levelty.domain.models.ChildInterestsItem
import com.example.levelty.domain.models.ChoresItem

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
    val childInterests: List<ChildInterestsItem?>? = null,
    val chores: List<ChoresItem?>? = null,
    val categoryId: Int? = null,
    val creatorId: Int? = null,
    val category: CategoryDTO? = null,
    val startDate: String? = null,
    val assigneeId: Int? = null,
    val status: String? = null
        )