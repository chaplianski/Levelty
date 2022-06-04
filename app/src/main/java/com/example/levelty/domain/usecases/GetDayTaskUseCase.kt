package com.example.levelty.domain.usecases

import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class GetDayTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    fun execute(): List<Task>{
        return taskRepository.getIncomingTasks()
    }
}