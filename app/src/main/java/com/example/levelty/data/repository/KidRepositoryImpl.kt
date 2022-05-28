package com.example.levelty.data.repository

import com.example.levelty.domain.models.Kid
import com.example.levelty.domain.repository.KidRepository
import javax.inject.Inject

class KidRepositoryImpl @Inject constructor(): KidRepository {

    override fun getKids(): List<Kid> {
        TODO("Not yet implemented")
    }
}