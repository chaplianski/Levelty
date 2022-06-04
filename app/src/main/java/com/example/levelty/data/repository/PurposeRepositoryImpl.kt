package com.example.levelty.data.repository

import com.example.levelty.domain.models.Purpose
import com.example.levelty.domain.repository.PurposeRepository
import javax.inject.Inject

class PurposeRepositoryImpl @Inject constructor(): PurposeRepository {
    override fun getParentsPurpose(): List<Purpose> {
        TODO("Not yet implemented")
    }
}