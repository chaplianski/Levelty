package com.example.levelty.data.storage.model


data class ChildDTO(
    val experienceForNextLevel: Int? = null,
    val availableGoalSlots: Int? = null,
    val balance: BalanceDTO? = null,
    val level: Int? = null,
    val id: Int? = null,
    val assignedTasks: List<AssignedTasksItemDTO?>? = null,
    val totalGoalSlots: Int? = null,
    val experience: Int? = null,
    val interests: List<InterestsItemDTO?>? = null,
    val user: UserDTO? = null,
    val parents: List<ParentsItemDTO?>? = null,
    val goals: List<GoalsItemDTO?>? = null
)
