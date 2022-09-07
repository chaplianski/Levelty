package com.example.levelty.data.repository

import com.example.levelty.data.storage.model.*
import com.example.levelty.domain.models.*

//class Mapper {
//}

fun TaskDTO.taskMapDataToDomain(): Task {
    return Task(
        taskId = taskId,
        taskName = taskName,
        taskCategory = taskCategory,
        taskPoints = taskPoints,
        taskDate = taskDate,
        taskStartTime = taskStartTime,
        taskRepeat = taskRepeat,
        taskParentPurpose = taskParentPurpose,
        taskKidsInterest = taskKidsInterest,
        kidName = kidName,
        taskStatus = taskStatus
    )
}

fun Task.taskMapDomainToData(): TaskDTO {
    return TaskDTO(
        taskId = taskId,
        taskName = taskName,
        taskCategory = taskCategory,
        taskPoints = taskPoints,
        taskDate = taskDate,
        taskStartTime = taskStartTime,
        taskRepeat = taskRepeat,
        taskParentPurpose = taskParentPurpose,
        taskKidsInterest = taskKidsInterest,
        kidName = kidName,
        taskStatus = taskStatus
    )
}

fun PurposeDTO.purposeMapDataToDomain(): Purpose {
    return Purpose(
        purposeId = purposeId,
        purposeName = purposeName
    )
}

fun InterestDTO.interestMapDataToDomain(): Interest {
    return Interest(
        interestId = interestId,
        interestName = interestName
    )
}

fun KidDTO.kidMapDataToDomain(): Kid {
    return Kid(
        kidId = kidId,
        kidName = kidName
    )
}

fun GoalDTO.goalMapDataToDomain(): Goal {
    return Goal(
        goalId = goalId,
        goalName = goalName,
        goalValue = goalValue,
        isApproval = isApproval,
        kidId = kidId
    )
}

fun ChildrenItemDTO.childrenMapDataToDomain(): ChildrenItem {
    return ChildrenItem(
        level = level,
        id = id,
        user = user?.userMapDataToDomain(),
        goals = goals?.map { it?.goalMapDataToDomain() }
    )
}

fun ChildrenItem.childrenMapDomainToData(): ChildrenItemDTO {
    return ChildrenItemDTO(
        level = level,
        id = id,
        user = user?.userMapDomainToData(),
        goals = goals?.map { it?.goalMapDomainToData() }
    )
}

fun GoalsItem.goalMapDomainToData(): GoalsItemDTO {
    return GoalsItemDTO(
        id = id,
        childId = childId,
        price = price,
        description = description,
        createdAt = createdAt,
        title = title,
        status = status
    )
}

fun GoalsItemDTO.goalMapDataToDomain(): GoalsItem {
    return GoalsItem(
        id = id,
        childId = childId,
        price = price,
        description = description,
        createdAt = createdAt,
        title = title,
        status = status
    )
}

fun UserDTO.userMapDataToDomain(): User {
    return User(
        id = id,
        countryCode = countryCode,
        notificationTypes = notificationTypes,
        isStaff = isStaff,
        dateOfBirth = dateOfBirth,
        phoneNumberConfirmed = phoneNumberConfirmed,
        lastName = lastName,
        phoneNumber = phoneNumber,
        language = language,
        firstName = firstName
    )
}

fun User.userMapDomainToData(): UserDTO {
    return UserDTO(
        id = id,
        countryCode = countryCode,
        notificationTypes = notificationTypes,
        isStaff = isStaff,
        dateOfBirth = dateOfBirth,
        phoneNumberConfirmed = phoneNumberConfirmed,
        lastName = lastName,
        phoneNumber = phoneNumber,
        language = language,
        firstName = firstName
    )
}

fun CreatedTasksItem.taskMapDomainToData(): CreatedTasksItemDTO {
    return CreatedTasksItemDTO(
        id = id,
        title = title,
        parentPurpose = parentPurpose,
        cost = cost,
        isPeriodic = isPeriodic,
        repeatInterval = repeatInterval,
        dueDate = dueDate,
        description = description,
        createdAt = createdAt,
        childInterests = childInterests?.map { it?.interestsDomainToData() },
        chores = chores?.map { it?.choreMapDataToDomain() },
        categoryId = categoryId,
        creatorId = creatorId,
        category = category?.categoryMapDomainToData(),
        startDate = startDate,
        assigneeId = assigneeId,
        status = status
    )
}

fun CreatedTasksItemDTO.taskMapDataToDomain(): CreatedTasksItem {
    return CreatedTasksItem(
        id = id,
        title = title,
        parentPurpose = parentPurpose,
        cost = cost,
        isPeriodic = isPeriodic,
        repeatInterval = repeatInterval,
        dueDate = dueDate,
        description = description,
        createdAt = createdAt,
        childInterests = childInterests?.map { it?.interestsDataToDomain() },
        chores = chores?.map { it?.choreMapDataToDomain() },
        categoryId = categoryId,
        creatorId = creatorId,
        category = category?.categoryMapDataToDomain(),
        startDate = startDate,
        assigneeId = assigneeId,
        status = status
    )
}

fun InterestsItemDTO.interestMapDataToDomain(): InterestsItem {
    return InterestsItem(
        id = id,
        title = title
    )
}

fun InterestsItem.interestMapDomainToData(): InterestsItemDTO {
    return InterestsItemDTO(
        id = id,
        title = title
    )
}

fun CategoryDTO.categoryMapDataToDomain(): Category {
    return Category(
        image = image,
        backgroundColor = backgroundColor,
        id = id,
        title = title
    )
}

fun Category.categoryMapDomainToData(): CategoryDTO {
    return CategoryDTO(
        image = image,
        backgroundColor = backgroundColor,
        id = id,
        title = title
    )
}

fun ChoresItemDTO.choreMapDataToDomain(): ChoresItem {
    return ChoresItem(
        date = date,
        taskId = taskId,
        finishedDatetime = finishedDatetime,
        comment = comment,
        id = id,
        status = status
    )
}

fun ChoresItem.choreMapDataToDomain(): ChoresItemDTO {
    return ChoresItemDTO(
        date = date,
        taskId = taskId,
        finishedDatetime = finishedDatetime,
        comment = comment,
        id = id,
        status = status
    )
}

fun ProcessedTaskDTO.processedTaskDataToDomain(): ProcessedTask {
    return  ProcessedTask(id = id,
    title = title,
    parentPurpose = parentPurpose,
    cost = cost,
    description = description,
    createdAt = createdAt,
    assigneeId = assigneeId,
    categoryId = categoryId,
    category = category?.categoryMapDataToDomain(),
    creatorId = creatorId,
    childInterests = childInterests?.map { it?.interestsDataToDomain() },
    status = status,
    choreDate = choreDate,
    choreFinishDate = choreFinishDate,
    choreStatus = choreStatus,
    choreID = choreID,
    choreComment = choreComment,)
}

fun ChildInterestsItemDTO.interestsDataToDomain(): ChildInterestsItem {
    return ChildInterestsItem(
        id =id,
        title = title
    )
}

fun ChildInterestsItem.interestsDomainToData(): ChildInterestsItemDTO {
    return ChildInterestsItemDTO(
        id =id,
        title = title
    )
}