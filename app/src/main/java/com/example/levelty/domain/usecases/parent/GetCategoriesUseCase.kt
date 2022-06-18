package com.example.levelty.domain.usecases.parent

import com.example.levelty.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    fun execute(): List<String>{
        return categoryRepository.getCategories()
    }
}