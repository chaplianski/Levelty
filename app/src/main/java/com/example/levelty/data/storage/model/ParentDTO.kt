package com.example.levelty.data.storage.model

import com.example.levelty.domain.models.User

data class ParentDTO(
    val children: List<ChildrenItemDTO?>? = null,
    val id: Int? = null,
    val user: User? = null,
    val createdTasks: List<CreatedTasksItemDTO?>? = null
)
