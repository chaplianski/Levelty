package com.example.levelty.data.storage.storage

import com.example.levelty.data.storage.model.KidDTO

interface KidStorage {

    fun getKids(): List<KidDTO>
}