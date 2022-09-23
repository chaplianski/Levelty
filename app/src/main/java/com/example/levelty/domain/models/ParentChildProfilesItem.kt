package com.example.levelty.domain.models

data class ParentChildProfilesItem (
    val childId: Int? = null,
    val parentId: Int? = null,
    val name: String? = null,
    val parentPurposesForChild: List<ParentPurposesForChildItem?>? = null,
    val id: Int? = null,
    val age: Int? = null
        )