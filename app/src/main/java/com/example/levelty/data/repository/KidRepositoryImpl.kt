package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.KidStorageImpl
import com.example.levelty.domain.models.ChildrenItem
import com.example.levelty.domain.models.Kid
import com.example.levelty.domain.repository.KidRepository
import javax.inject.Inject

class KidRepositoryImpl @Inject constructor(private val kidStorageImpl: KidStorageImpl): KidRepository {

    override fun getKids(): List<ChildrenItem> {
        return kidStorageImpl.getKids().map { it.childrenMapDataToDomain() }
    }

    override fun getKid(kidId: Int): ChildrenItem {
        return kidStorageImpl.getKid(kidId).childrenMapDataToDomain()
    }
}