package com.example.levelty.domain.repository

import com.example.levelty.domain.models.ChildrenItem
import com.example.levelty.domain.models.Kid

interface KidRepository {

    suspend fun getKids (): List<ChildrenItem>

    fun getKid(kidId: Int): ChildrenItem
}