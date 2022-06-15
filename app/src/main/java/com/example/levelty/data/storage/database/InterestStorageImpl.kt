package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.InterestDTO
import com.example.levelty.data.storage.storage.InterestStorage
import javax.inject.Inject

class InterestStorageImpl @Inject constructor(): InterestStorage {

    override fun getKidInterests(): List<InterestDTO> {
        val interestList = mutableListOf<InterestDTO>()
        interestList.add(InterestDTO(0, "Books"))
        interestList.add(InterestDTO(1, "Robots"))
        interestList.add(InterestDTO(2, "Bugs"))
        interestList.add(InterestDTO(3, "Cars"))
        return interestList.toList()
    }

    override fun getKidsInterestsChoose(): List<InterestDTO> {
        val interestList = mutableListOf<InterestDTO>()
        interestList.add(InterestDTO(0, "Books"))
        interestList.add(InterestDTO(1, "Robots"))
        interestList.add(InterestDTO(2, "Bugs"))
        interestList.add(InterestDTO(3, "Cars"))
        return interestList.toList()
    }


}