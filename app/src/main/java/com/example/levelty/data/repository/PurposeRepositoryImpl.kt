package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.PurposeStorageImpl
import com.example.levelty.domain.models.Purpose
import com.example.levelty.domain.repository.PurposeRepository
import javax.inject.Inject

class PurposeRepositoryImpl @Inject constructor(private val purposeStorageImpl: PurposeStorageImpl): PurposeRepository {

    override fun getParentsPurpose(): List<Purpose> {
        return purposeStorageImpl.getParentsPurpose().map { it.purposeMapDataToDomain() }
    }

    override fun getParentsPurposes(): List<Purpose> {
        return purposeStorageImpl.getParentsPurposes().map { it.purposeMapDataToDomain() }
    }
}