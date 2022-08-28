package com.example.levelty.domain.models

data class ChildrenItem(
    val level: Int? = null,
    val id: Int? = null,
    val user: User? = null,
    val goals: List<GoalsItem?>? = null
)
