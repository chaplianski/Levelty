package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Kid

interface KidRepository {

    fun  getKids (): List<Kid>
}