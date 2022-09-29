package com.example.levelty.domain.models
//TODO тут пора бы переходить уже на какие-то значения по умолчанию если возможно потмоу что столько нулей потом придется элвис операторами обрабатывать что усложняет логику
data class AssignedTasksItem(
    val cost: Int? = null,
    val dueDate: String? = null,
    val description: String? = null,
    val parentPurpose: String? = null,
    val createdAt: String? = null,
    val childInterests: List<ChildInterestsItem?>? = null,
    val chores: List<ChoresItem?>? = null,
    val title: String? = null,
    val categoryId: Int? = null,
    val creatorId: Int? = null,
    val repeatInterval: Int? = null,
    val id: Int? = null,
    val category: Category? = null,
    val isPeriodic: Boolean? = null,
    val startDate: String? = null,
    val assigneeId: Int? = null,
    val status: String? = null
)
