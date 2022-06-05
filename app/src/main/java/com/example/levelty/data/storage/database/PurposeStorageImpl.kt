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
}