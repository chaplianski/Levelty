package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.CategoryStorageImpl
import com.example.levelty.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryStorageImpl: CategoryStorageImpl): CategoryRepository{

    override fun getCategories(): List<String> {
        return categoryStorageImpl.getCategoriesList()
    }
}