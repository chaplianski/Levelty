package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.InterestStorageImpl
import com.example.levelty.domain.models.Interest
import com.example.levelty.domain.repository.InterestRepository
import javax.inject.Inject

class InterestRepositoryImpl @Inject constructor(private val interestStorageImpl: InterestStorageImpl): InterestRepository {
    override fun getKidInterests(): List<Interest> {
        return interestStorageImpl.getKidInterests().map { it.interestMapDataToDomain() }
    }
}