package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Purpose

interface PurposeRepository {

    fun getParentsPurpose(): List<Purpose>//TODO change name

    fun getParentsPurposes(): List<String>
}