package com.example.levelty.data.storage.response

import com.example.levelty.data.storage.model.CategoryDTO
import com.example.levelty.domain.models.ChoresItem
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.domain.models.MetaData

data class GetChildrenResponse(
	val data: List<DataItem?>? = null
)

data class ChildInterestsItem(
	val name: String? = null,
	val title: String? = null
)

data class User(
	val countryCode: String? = null,
	val notificationTypes: List<String?>? = null,
	val isStaff: Boolean? = null,
	val dateOfBirth: String? = null,
	val phoneNumberConfirmed: Boolean? = null,
	val name: String? = null,
	val phoneNumber: String? = null,
	val language: String? = null,
	val id: Int? = null
)

data class DataItem(
	val experienceForNextLevel: Int? = null,
	val avatarId: String? = null,
	val level: Int? = null,
	val metaData: MetaData? = null,
	val id: Int? = null,
	val assignedTasks: List<AssignedTasksItem?>? = null,
	val experience: Int? = null,
	val user: User? = null,
	val goals: List<GoalsItem?>? = null
)



data class AssignedTasksItem(
	val cost: Int? = null,
	val dueDate: String? = null,
	val childInterests: List<ChildInterestsItem?>? = null,
	val description: String? = null,
	val parentPurpose: String? = null,
	val createdAt: String? = null,
	val chores: List<ChoresItem?>? = null,
	val title: String? = null,
	val creatorId: Int? = null,
	val repeatInterval: Int? = null,
	val id: Int? = null,
	val category: CategoryDTO? = null,
	val isPeriodic: Boolean? = null,
	val startDate: String? = null,
	val assigneeId: Int? = null,
	val status: String? = null
)

