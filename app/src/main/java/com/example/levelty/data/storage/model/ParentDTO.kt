package com.example.levelty.data.storage.model

data class ParentDTO(
    val children: List<ChildrenItemDTO?>? = null,
    val id: Int? = null,
    val user: UserDTO? = null,
    val createdTasks: List<CreatedTasksItemDTO?>? = null,
    val parentChildProfiles: List<ParentChildProfilesItemDTO?>? = null

)
