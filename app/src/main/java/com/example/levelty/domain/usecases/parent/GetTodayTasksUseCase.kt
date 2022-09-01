package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class GetTodayTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    fun execute(kidId: Int, date: String): List<CreatedTasksItem>{
        return taskRepository.getTodayTasks(kidId, date)
    }
}