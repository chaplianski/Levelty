package com.example.levelty.data.storage.database

import android.util.Log
import com.example.levelty.data.storage.dao.LeveltyDao
import com.example.levelty.data.storage.model.TaskDTO
import com.example.levelty.data.storage.storage.TaskStorage
import javax.inject.Inject

class TaskStorageImpl @Inject constructor(): TaskStorage {

    @Inject
    lateinit var leveltyDao: LeveltyDao

    override fun getDayTask(kidName: String, date: String): List<TaskDTO> {
        return leveltyDao.getTodayTask(kidName, date)

//        val taskList = mutableListOf<TaskDTO>()
//        val dateToday = "" //Calendar.getInstance().timeInMillis
//        val startDate = "" //Calendar.getInstance().timeInMillis
//        taskList.add(TaskDTO(0, "Find key", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "0L", "Need approval"))
//        taskList.add(TaskDTO(1, "Walk dog", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "1L", "Need approval"))
//        return taskList.toList()
    }

    override fun getTodayTask(kidName: String, date: String): List<TaskDTO> {
        val list = leveltyDao.getTodayTask(kidName, date)
//        Log.d("MyLog", "list in taskStorageImpl = $list")
        return list

//        val taskList = mutableListOf<TaskDTO>()
//        val dateToday = "" //Calendar.getInstance().timeInMillis
//        val startDate = "" //Calendar.getInstance().timeInMillis
//        taskList.add(TaskDTO(0, "Find key", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "0L", "Need approval"))
//        taskList.add(TaskDTO(1, "Walk dog", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "1L", "Need approval"))
//        return taskList.toList()
    }

    override fun getTasksList(): List<TaskDTO> {
        val taskList = mutableListOf<TaskDTO>()
        return leveltyDao.getTasksList()

//        val dateToday = "" //Calendar.getInstance().timeInMillis
//        val startDate = "" //Calendar.getInstance().timeInMillis
//        taskList.add(TaskDTO(0, "0", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew", "Need approval"))
//        taskList.add(TaskDTO(1, "1", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "0L","Need approval"))
//        taskList.add(TaskDTO(2, "2", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "0L","Need approval"))
//        taskList.add(TaskDTO(3, "3", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "0L","Need approval"))
//        taskList.add(TaskDTO(4, "4", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "0L","Need approval"))
//        taskList.add(TaskDTO(5, "5", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(6, "6", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(7, "7", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(8, "8", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(9, "9", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(10, "10", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(11, "11", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(12, "12", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(13, "13", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(14, "14", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(15, "15", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(16, "16", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(17, "17", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(18, "18", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
//        taskList.add(TaskDTO(19, "19", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
//        return taskList.toList()
    }

    override fun getKidDetailTasksList(): List<TaskDTO> {
        val taskList = mutableListOf<TaskDTO>()
        val dateToday = "" //Calendar.getInstance().timeInMillis
        val startDate = "" //Calendar.getInstance().timeInMillis
        taskList.add(TaskDTO(0, "Find key", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
        taskList.add(TaskDTO(1, "Walk dog", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
        taskList.add(TaskDTO(2, "Find key", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
        taskList.add(TaskDTO(3, "Walk dog", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
        taskList.add(TaskDTO(4, "Find key", "Home", 1, dateToday, startDate, "Weekly", "Order", "Finding scill", "Andrew","Need approval"))
        taskList.add(TaskDTO(5, "Walk dog", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "Andrew","Need approval"))
        return taskList.toList()
    }

    override fun addTask(taskDTO: TaskDTO) {
        leveltyDao.insertTask(taskDTO)
    }

    override fun updateTask(taskDTO: TaskDTO) {
        leveltyDao.updateTask(taskDTO)
    }


}