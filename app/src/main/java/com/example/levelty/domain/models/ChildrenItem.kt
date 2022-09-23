package com.example.levelty.domain.models

data class ChildrenItem(
    val experienceForNextLevel: Int? = null,
    val avatarId: String? = null,
    val level: Int? = null,
    val metaData: MetaData? = null,
    val id: Int? = null,
    val experience: Int? = null,
    val user: User? = null,
    val goals: List<GoalsItem?>? = null
)
