package com.example.levelty.domain.models

data class Children(
	val balance: Balance? = null,
	val level: Int? = null,
	val id: Int? = null,
	val assignedTasks: List<AssignedTasksItem?>? = null,
	val interests: List<InterestsItem?>? = null,
	val user: User? = null,
	val parents: List<ParentsItem?>? = null,
	val goals: List<GoalsItem?>? = null
)





