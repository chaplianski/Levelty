package com.example.levelty.data.storage.storage

import com.example.levelty.data.storage.model.PurposeDTO
import com.example.levelty.domain.models.Purpose

interface PurposeStorage {

    fun getParentsPurpose(): List<PurposeDTO>

    fun getParentsPurposes(): List<PurposeDTO>
}