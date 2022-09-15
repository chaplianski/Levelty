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
        childInterests = childInterests?.map { it?.interestsMapDomainToData() },
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
        childInterests = childInterests?.map { it?.interestsMapDataToDomain() },
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

fun ParentProcessedTaskDTO.processedMapTaskDataToDomain(): ParentProcessedTask {
    return ParentProcessedTask(
        id = id,
        title = title,
        parentPurpose = parentPurpose,
        cost = cost,
        description = description,
        createdAt = createdAt,
        assigneeId = assigneeId,
        categoryId = categoryId,
        category = category?.categoryMapDataToDomain(),
        creatorId = creatorId,
        isPeriodic = isPeriodic,
        repeatInterval = repeatInterval,
        childInterests = childInterests?.map { it?.interestsMapDataToDomain() },
        status = status,
        choreDate = choreDate,
        choreFinishDate = choreFinishDate,
        choreStatus = choreStatus,
        choreID = choreID,
        choreComment = choreComment,
    )
}

fun ChildInterestsItemDTO.interestsMapDataToDomain(): ChildInterestsItem {
    return ChildInterestsItem(
        id = id,
        title = title
    )
}

fun ChildInterestsItem.interestsMapDomainToData(): ChildInterestsItemDTO {
    return ChildInterestsItemDTO(
        id = id,
        title = title
    )
}

fun NewTask.taskMapDomainToData(): NewTaskDTO {
    return NewTaskDTO(
        childInterestIds = childInterestIds,
        cost = cost,
        categoryId = categoryId,
        dueDate = dueDate,
        description = description,
        parentPurpose = parentPurpose,
        customSchedule = customSchedule,
        repeatInterval = repeatInterval,
        title = title,
        isPeriodic = isPeriodic,
        startDate = startDate,
        assigneeId = assigneeId
    )
}

fun Child.childMapDomainToData(): ChildDTO {
    return ChildDTO(
        experienceForNextLevel = experienceForNextLevel,
        availableGoalSlots = availableGoalSlots,
        balance = balance?.balanceMapDomainToData(),
        level = level,
        id = id,
        assignedTasks = assignedTasks?.map { it?.assignedTaskMapDomainToData() },
        totalGoalSlots = totalGoalSlots,
        experience = experience,
        interests = interests?.map { it?.interestMapDomainToData() },
        user = user?.userMapDomainToData(),
        parents = parents?.map { it?.parentMapDomainToData() },
        goals = goals?.map { it?.goalMapDomainToData() }
    )
}

fun ChildDTO.childMapDomainToData(): Child {
    return Child(
        experienceForNextLevel = experienceForNextLevel,
        availableGoalSlots = availableGoalSlots,
        balance = balance?.balanceMapDomainToData(),
        level = level,
        id = id,
        assignedTasks = assignedTasks?.map { it?.assignedTaskMapDataToDomain() },
        totalGoalSlots = totalGoalSlots,
        experience = experience,
        interests = interests?.map { it?.interestMapDataToDomain() },
        user = user?.userMapDataToDomain(),
        parents = parents?.map { it?.parentMapDataToDomain() },
        goals = goals?.map { it?.goalMapDataToDomain() }
    )
}

fun Parent.parentMapDomainToData(): ParentDTO {
    return ParentDTO(
        children = children?.map { it?.childrenMapDomainToData() },
        id = id,
        user = user,
        createdTasks = createdTasks?.map { it?.taskMapDomainToData() }
    )
}

fun ParentDTO.parentMapDataToDomain(): Parent {
    return Parent(
        children = children?.map { it?.childrenMapDataToDomain() },
        id = id,
        user = user,
        createdTasks = createdTasks?.map { it?.taskMapDataToDomain() }
    )
}

fun ParentsItem.parentMapDomainToData(): ParentsItemDTO{
    return ParentsItemDTO(
        id = id
    )
}

fun ParentsItemDTO.parentMapDataToDomain(): ParentsItem{
    return ParentsItem(
        id = id
    )
}

fun Balance.balanceMapDomainToData(): BalanceDTO{
    return BalanceDTO(
        amount = amount,
        childId = childId
    )
}

fun BalanceDTO.balanceMapDomainToData(): Balance{
    return Balance(
        amount = amount,
        childId = childId
    )
}

fun AssignedTasksItem.assignedTaskMapDomainToData(): AssignedTasksItemDTO{
    return AssignedTasksItemDTO(
        cost = cost,
        dueDate = dueDate,
        description = description,
        parentPurpose = parentPurpose,
        createdAt = createdAt,
        childInterests = childInterests?.map { it?.interestsMapDomainToData() },
        chores = chores?.map { it?.choreMapDataToDomain() },
        title = title,
        categoryId = categoryId,
        creatorId = creatorId,
        repeatInterval = repeatInterval,
        id = id,
        category = category?.categoryMapDomainToData(),
        isPeriodic = isPeriodic,
        startDate = startDate
    )
}

fun AssignedTasksItemDTO.assignedTaskMapDataToDomain(): AssignedTasksItem{
    return AssignedTasksItem(
        cost = cost,
        dueDate = dueDate,
        description = description,
        parentPurpose = parentPurpose,
        createdAt = createdAt,
        childInterests = childInterests?.map { it?.interestsMapDataToDomain() },
        chores = chores?.map { it?.choreMapDataToDomain() },
        title = title,
        categoryId = categoryId,
        creatorId = creatorId,
        repeatInterval = repeatInterval,
        id = id,
        category = category?.categoryMapDataToDomain(),
        isPeriodic = isPeriodic,
        startDate = startDate
    )
}

fun KidProcessedTaskDTO.kidProcessedTaskMapDataToDomain(): KidProcessedTask{
    return KidProcessedTask(
        id = id,
        title = title,
        parentPurpose = parentPurpose,
        cost = cost,
        description = description,
        createdAt = createdAt,
        assigneeId = assigneeId,
        categoryId = categoryId,
        category = category?.categoryMapDataToDomain(),
        creatorId = creatorId,
        isPeriodic = isPeriodic,
        repeatInterval = repeatInterval,
        childInterests = childInterests?.map { it?.interestsMapDataToDomain() },
        status = status,
        choreDate = choreDate,
        choreFinishDate = choreFinishDate,
        choreStatus = choreStatus,
        choreID = choreID,
        choreComment = choreComment

    )
}