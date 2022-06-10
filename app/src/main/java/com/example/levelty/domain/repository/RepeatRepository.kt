package com.example.levelty.domain.repository

import com.example.levelty.domain.models.Repeat

interface RepeatRepository {

    fun getRepeatVariants(): List<Repeat>
}