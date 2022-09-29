package com.example.levelty.data.storage.dao

import androidx.room.*
import com.example.levelty.data.storage.model.TaskDTO

@Dao
abstract class LeveltyDao {//TODO почему класс называется Levelty если везде дальше только task работает?)
//TODO это обычно удаляют и код в истории гита прекрасно хранится
//    @Query("SELECT * FROM briefcase WHERE briefCaseId= :id")
//    abstract fun getBriefCase(id: Long): BriefCaseData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertTask(taskDTO: TaskDTO)

    @Query ("SELECT * FROM tasks")
    abstract fun getTasksList(): List<TaskDTO>//TODO если в названии переменной уже есть list то не зачем добавлять окончание -s я бы использовал такое название функции getTaskList

    @Query("SELECT * FROM tasks WHERE kidName=:kidName AND taskDate=:date")
    abstract fun getTodayTask(kidName: String, date: String): List<TaskDTO>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateTask(taskDTO: TaskDTO)
}