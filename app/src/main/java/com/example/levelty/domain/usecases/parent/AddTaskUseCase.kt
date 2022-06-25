package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.Task
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository){

    fun execute(task: Task){
        taskRepository.addTask(task)
    }
}