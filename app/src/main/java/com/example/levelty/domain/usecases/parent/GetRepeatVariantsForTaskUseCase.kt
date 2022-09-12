package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.repository.TaskRepository
import javax.inject.Inject

class GetRepeatVariantsForTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    fun execute(): List <String> {
        return taskRepository.getRepeatVariants()
    }
}