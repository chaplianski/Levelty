package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Purpose

interface PurposeRepository {

    fun getParentsPurpose(): List<Purpose>

    fun getParentsPurposes(): List<String>
}