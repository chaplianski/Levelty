package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.models.ParentProcessedTask
import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    //    fun execute(): List<CreatedTasksItem>{
//        return taskRepository.getTasksList()
//    }
    fun execute(): List<ParentProcessedTask> {
        return taskRepository.getTasksList()
    }

}