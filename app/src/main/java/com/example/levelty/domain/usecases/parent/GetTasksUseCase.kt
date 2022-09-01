package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    fun execute(): List<CreatedTasksItem>{
        return taskRepository.getTasksList()

    }
}