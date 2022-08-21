package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.KidDTO
import com.example.levelty.data.storage.storage.KidStorage
import javax.inject.Inject

class KidStorageImpl @Inject constructor(): KidStorage {

    override fun getKids(): List<KidDTO> {
        val kidList = mutableListOf<KidDTO>()
        kidList.add(KidDTO(0, "Andrew"))
        kidList.add(KidDTO(1, "Maxim"))
        return kidList.toList()
    }

    override fun getKid(kidId: Long): KidDTO {
        return KidDTO(0, "Andrew")
    }
}