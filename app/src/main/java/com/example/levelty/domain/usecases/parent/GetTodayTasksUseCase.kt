package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class GetTodayTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    fun execute(kidName: String, date: String): List<Task>{
        return taskRepository.getTodayTasks(kidName, date)
    }
}