package com.example.levelty.data.storage.model

data class ParentChildProfilesItemDTO(
    val childId: Int? = null,
    val parentId: Int? = null,
    val name: String? = null,
    val parentPurposesForChild: List<ParentPurposesForChildItemDTO?>? = null,
    val id: Int? = null,
    val age: Int? = null
)
