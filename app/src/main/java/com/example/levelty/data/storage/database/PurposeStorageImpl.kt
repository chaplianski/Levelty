package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.model.PurposeDTO
import com.example.levelty.data.storage.storage.PurposeStorage
import javax.inject.Inject

class PurposeStorageImpl @Inject constructor(): PurposeStorage {

    override fun getParentsPurpose(): List<PurposeDTO> {
        val purposeList = mutableListOf<PurposeDTO>()
        purposeList.add(PurposeDTO(0, "Better school marks"))
        purposeList.add(PurposeDTO(1, "Good view"))
        purposeList.add(PurposeDTO(2, "Having friendship"))
        return purposeList.toList()
    }

    override fun getParentsPurposes(): List<PurposeDTO> {
        val purposeList = mutableListOf<PurposeDTO>()
        purposeList.add(PurposeDTO(0, "Home help"))
        purposeList.add(PurposeDTO(1, "Autonomy"))
        purposeList.add(PurposeDTO(2, "Physical growth"))
        purposeList.add(PurposeDTO(3, "Creativity"))
        purposeList.add(PurposeDTO(4, "Better school marks"))
        purposeList.add(PurposeDTO(5, "Having friendship"))
        return purposeList.toList()
    }
}