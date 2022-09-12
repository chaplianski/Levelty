package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Child
import com.example.levelty.domain.models.ChildrenItem
import com.example.levelty.domain.models.Kid

interface KidRepository {

    suspend fun getKids (): List<ChildrenItem>

    fun getChild(): Child


    fun getKid(kidId: Int): ChildrenItem


}