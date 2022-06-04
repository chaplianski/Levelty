package com.example.levelty.data.repository

import com.example.levelty.domain.models.Interest
import com.example.levelty.domain.repository.InterestRepository
import javax.inject.Inject

class InterestRepositoryImpl @Inject constructor(): InterestRepository {
    override fun getKidInterests(): List<Interest> {
        TODO("Not yet implemented")
    }
}