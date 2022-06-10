package com.example.levelty.domain.repository

interface CategoryRepository {

    fun getCategories(): List<String>
}