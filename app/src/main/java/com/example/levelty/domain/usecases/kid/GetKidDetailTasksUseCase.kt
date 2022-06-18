package com.example.levelty.domain.usecases.kid

import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class GetKidDetailTasksUseCase  @Inject constructor(private val taskRepository: TaskRepository) {


    fun execute(): List<Task>{
        return taskRepository.getKidDetailTasksList()

    }
}