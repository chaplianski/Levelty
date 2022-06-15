package com.example.levelty.data.storage.storage

import com.example.levelty.data.storage.model.InterestDTO

interface InterestStorage {

    fun getKidInterests(): List<InterestDTO>

    fun getKidsInterestsChoose(): List<InterestDTO>
}