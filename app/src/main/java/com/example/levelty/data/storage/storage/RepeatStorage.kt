package com.example.levelty.data.storage.storage

import com.example.levelty.domain.models.Repeat

interface RepeatStorage {

    fun getRepeatList(): List<Repeat>
}