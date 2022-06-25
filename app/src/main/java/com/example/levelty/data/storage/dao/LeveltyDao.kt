package com.example.levelty.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.levelty.data.storage.model.TaskDTO

@Dao
abstract class LeveltyDao {

//    @Query("SELECT * FROM briefcase WHERE briefCaseId= :id")
//    abstract fun getBriefCase(id: Long): BriefCaseData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertTask(taskDTO: TaskDTO)

    @Query ("SELECT * FROM tasks")
    abstract fun getTasksList(): List<TaskDTO>

    @Query("SELECT * FROM tasks WHERE kidName=:kidName AND taskDate=:date")
    abstract fun getTodayTask(kidName: String, date: String): List<TaskDTO>
}