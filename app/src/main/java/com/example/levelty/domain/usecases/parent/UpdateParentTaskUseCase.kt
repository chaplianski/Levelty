package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.NewTask
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateParentTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun execute(taskId: Int, newTask: NewTask){
        taskRepository.updateParentTask(taskId, newTask)
    }
}