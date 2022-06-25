package com.example.levelty.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.levelty.data.storage.dao.LeveltyDao
import com.example.levelty.data.storage.model.KidDTO
import com.example.levelty.data.storage.model.TaskDTO


@Database(
    entities = [
       KidDTO::class,
       TaskDTO::class,
//        PhotosData::class,
//        NotesData::class
               ],
    version = 1,
    exportSchema = false
)
abstract class LeveltyDB: RoomDatabase() {
    abstract fun leveltyDao(): LeveltyDao

}