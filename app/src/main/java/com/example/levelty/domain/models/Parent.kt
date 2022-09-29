package com.example.levelty.domain.models

data class Parent(
	val children: List<ChildrenItem?>? = null,
	val id: Int? = null,
	val user: User? = null,
	val createdTasks: List<CreatedTasksItem?>? = null
)











