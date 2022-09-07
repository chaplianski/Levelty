package com.example.levelty.data.storage.model

import com.example.levelty.domain.models.Category
import com.example.levelty.domain.models.ChildInterestsItem

data class ProcessedTaskDTO(

    val id: Int? = null,
    val title: String? = null,
    val parentPurpose: String? = null,
    val cost: Int? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val assigneeId: Int? = null,
    val categoryId: Int? = null,
    val category: CategoryDTO? = null,
    val creatorId: Int? = null,
    val childInterests: List<ChildInterestsItemDTO?>? = null,
    val status: String? = null,
    val choreDate: String? = null,
    val choreFinishDate: String? = null,
    val choreStatus: String? = null,
    val choreID: Int? = null,
    val choreComment: String? = null,

    )
