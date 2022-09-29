package com.example.levelty.data.storage.model

import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.domain.models.User

data class ChildrenItemDTO(
    val level: Int? = null,
    val id: Int? = null,
    val user: UserDTO? = null,
    val goals: List<GoalsItemDTO?>? = null
)
