package com.example.levelty.domain.usecases.kid

import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateChoreStatusUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend fun execute(choreId: Int){
        taskRepository.updateChoreStatus(choreId)
    }
}