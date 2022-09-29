package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.storage.CategoryStorage
import javax.inject.Inject

class CategoryStorageImpl @Inject constructor() : CategoryStorage {

    override fun getCategoriesList(): List<String> {
        val categoryList = listOf(
            "Health amd sport",
            "Everyday tasks",
            "Day planning",
            "School",
            "Communication",
            "Hobbies",
            "Education and knowledge",
            "Creativity",
            "Home"
        )
        return categoryList
    }
}