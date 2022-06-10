package com.example.levelty.data.storage.database

import com.example.levelty.data.storage.storage.RepeatStorage
import com.example.levelty.domain.models.Repeat
import javax.inject.Inject

class RepeatStorageImpl @Inject constructor(): RepeatStorage {


    override fun getRepeatList(): List<Repeat> {
        val repeatList = mutableListOf<Repeat>()
        repeatList.add(Repeat(0,"Doesn't repeat"))
        repeatList.add(Repeat(1, "Daily"))
        repeatList.add(Repeat(2, "Weekly"))
        repeatList.add(Repeat(3,"Every Monthly"))
        return repeatList.toList()

    }
}