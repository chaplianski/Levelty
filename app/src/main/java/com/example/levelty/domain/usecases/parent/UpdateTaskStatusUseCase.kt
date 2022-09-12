package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskStatusUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun execute(taskId: Int, status: String){
        taskRepository.updateTaskStatus(taskId, status)
    }
}