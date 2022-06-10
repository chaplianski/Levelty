package com.example.levelty.data.repository

import com.example.levelty.data.storage.database.RepeatStorageImpl
import com.example.levelty.domain.models.Repeat
import com.example.levelty.domain.repository.RepeatRepository
import javax.inject.Inject

class RepeatRepositoryImpl @Inject constructor(private val repeatStorageImpl: RepeatStorageImpl): RepeatRepository {

    override fun getRepeatVariants(): List<Repeat> {
        return repeatStorageImpl.getRepeatList()
    }
}