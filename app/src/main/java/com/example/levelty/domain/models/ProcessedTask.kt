package com.example.levelty.domain.models

data class ProcessedTask(
    val id: Int? = null,
    val title: String? = null,
    val parentPurpose: String? = null,
    val cost: Int? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val assigneeId: Int? = null,
    val categoryId: Int? = null,
    val category: Category? = null,
    val creatorId: Int? = null,
    val childInterests: List<ChildInterestsItem?>? = null,
    val status: String? = null,
    val choreDate: String? = null,
    val choreFinishDate : String? = null,
    val choreStatus: String? = null,
    val choreID: Int? = null,
    val choreComment: String? = null,

)
