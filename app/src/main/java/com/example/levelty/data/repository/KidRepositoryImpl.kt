package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.KidStorageImpl
import com.example.levelty.domain.models.Kid
import com.example.levelty.domain.repository.KidRepository
import javax.inject.Inject

class KidRepositoryImpl @Inject constructor(private val kidStorageImpl: KidStorageImpl): KidRepository {

    override fun getKids(): List<Kid> {
        return kidStorageImpl.getKids().map { it.kidMapDataToDomain() }
    }
}