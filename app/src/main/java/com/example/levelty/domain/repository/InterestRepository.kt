package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Interest

interface InterestRepository {

    fun getKidInterests(): List<Interest>

    fun getKidsInterestChoose(): List<Interest>
}