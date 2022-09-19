package com.example.levelty.data.storage.database

import android.util.Log
import com.example.levelty.data.storage.dao.LeveltyDao
import com.example.levelty.data.storage.model.*
import com.example.levelty.data.storage.storage.TaskStorage
import com.example.levelty.domain.models.ChoresItem
import com.example.levelty.domain.models.CreatedTasksItem
import javax.inject.Inject
import javax.inject.Singleton

class TaskStorageImpl @Inject constructor() : TaskStorage {

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

    override fun getTodayTask(kidId: Int, date: String): List<CreatedTasksItemDTO> {
        val category1 = CategoryDTO(null, "#AED581", 0, "School")
        val category2 = CategoryDTO(null, "#EA80FC", 1, "Science")
        val category3 = CategoryDTO(null, "#FF7043", 1, "Sport")

        val listTasks = mutableListOf<CreatedTasksItemDTO>()
        listTasks.add(
            CreatedTasksItemDTO(
                0,
                "Walk dog",
                "Home help",
                15,
                true,
                1,
                "October 12 2022",
                "Any description",
                "Father",
                null,
                null,
                1,
                0,
                category1,
                "September 01 2022",
                0,
                "need approved"
            )
        )
        listTasks.add(
            CreatedTasksItemDTO(
                1,
                "Go to school",
                "Autonomy",
                10,
                true,
                1,
                "October 12 2022",
                "Any description",
                "Father",
                null,
                null,
                0,
                0,
                category2,
                "September 01 2022",
                0,
                "created"
            )
        )

//        val list = leveltyDao.getTodayTask(kidName, date)
//        Log.d("MyLog", "list in taskStorageImpl = $list")
        return listTasks

//        val taskList = mutableListOf<TaskDTO>()
//        val dateToday = "" //Calendar.getInstance().timeInMillis
//        val startDate = "" //Calendar.getInstance().timeInMillis
//        taskList.add(TaskDTO(0, "Find key", "Home", 2, dateToday, startDate, "Weekly", "Order", "Finding scill", "0L", "Need approval"))
//        taskList.add(TaskDTO(1, "Walk dog", "Home", 3, dateToday, startDate, "Weekly", "Order", "Animal scill", "1L", "Need approval"))
//        return taskList.toList()
    }

    override fun getCreatedTasksList(): List<CreatedTasksItemDTO> {
//        val taskList = mutableListOf<TaskDTO>()
//        return leveltyDao.getTasksList()
        val choresList = mutableListOf<ChoresItemDTO>()
        choresList.add(ChoresItemDTO("2022-09-16", 0, "2022-09-20", "Nothing", 0, "done"))
        choresList.add(ChoresItemDTO("2022-09-19", 0, "2022-09-20", "Nothing", 0, "pending"))
        choresList.add(ChoresItemDTO("2022-09-15", 0, "2022-09-20", "Nothing", 0, "refused"))
        choresList.add(ChoresItemDTO("2022-09-18", 0, "2022-09-20", "Nothing", 0, "rejected"))
        choresList.add(
            ChoresItemDTO(
                "2022-09-18",
                0,
                "2022-09-20",
                "Nothing",
                0,
                "waiting_for_approval"
            )
        )

        val choresList2 = mutableListOf<ChoresItemDTO>()
        choresList2.add(ChoresItemDTO("2022-09-16", 1, "2022-09-20", "Nothing", 0, "pending"))
        choresList2.add(ChoresItemDTO("2022-09-17", 1, "2022-09-20", "Nothing", 0, "pending"))
        choresList2.add(ChoresItemDTO("2022-09-18", 1, "2022-09-20", "Nothing", 0, "refused"))

        val category1 = CategoryDTO(null, "#AED581", 0, "School")
        val category2 = CategoryDTO(null, "#EA80FC", 1, "Science")
        val category3 = CategoryDTO(null, "#FF7043", 1, "Sport")

        val listTasks = mutableListOf<CreatedTasksItemDTO>()
        listTasks.add(
            CreatedTasksItemDTO(
                0,
                "Walk dog",
                "Home help",
                15,
                false,
                0,
                "2022-09-25",
                "Any description",
                "Father",
                null,
                choresList.toList(),
                1,
                0,
                category1,
                "2022-08-25",
                0,
                "pending"
            )
        )
        listTasks.add(
            CreatedTasksItemDTO(
                1,
                "Go to school",
                "Autonomy",
                55,
                true,
                1,
                "2022-10-01",
                "Any description",
                "Father",
                null,
                choresList2.toList(),
                0,
                0,
                category2,
                "2022-09-02",
                0,
                "done"
            )
        )

//        val list = leveltyDao.getTodayTask(kidName, date)
//        Log.d("MyLog", "list in taskStorageImpl = $list")
        return listTasks
    }

    override fun getAssignedTaskList(): List<AssignedTasksItemDTO> {
        val choresList = mutableListOf<ChoresItemDTO>()
        choresList.add(ChoresItemDTO("2022-09-19", 0, "2022-09-20", "Nothing", 0, "done"))
        choresList.add(ChoresItemDTO("2022-09-19", 0, "2022-09-20", "Nothing", 0, "pending"))
        choresList.add(ChoresItemDTO("2022-09-16", 0, "2022-09-20", "Nothing", 0, "refused"))
        choresList.add(ChoresItemDTO("2022-09-17", 0, "2022-09-20", "Nothing", 0, "rejected"))
        choresList.add(
            ChoresItemDTO(
                "2022-09-19",
                0,
                "2022-09-20",
                "Nothing",
                0,
                "waiting_for_approval"
            )
        )
//        choresList.add(ChoresItemDTO("2022-09-16", 0, "2022-09-20", "Nothing", 0, "waiting_for_approval"))
//        choresList.add(ChoresItemDTO("2022-09-16", 0, "2022-09-20", "Nothing", 0, "pending"))
//        choresList.add(ChoresItemDTO("2022-09-16", 0, "2022-09-20", "Nothing", 0, "refused"))
//        choresList.add(ChoresItemDTO("2022-09-16", 0, "2022-09-20", "Nothing", 0, "rejected"))

        val choresList2 = mutableListOf<ChoresItemDTO>()
        choresList2.add(ChoresItemDTO("2022-09-12", 1, "2022-09-20", "Nothing", 0, "pending"))
        choresList2.add(ChoresItemDTO("2022-09-13", 1, "2022-09-20", "Nothing", 0, "pending"))
        choresList2.add(ChoresItemDTO("2022-09-14", 1, "2022-09-20", "Nothing", 0, "refused"))

        val choresList3 = mutableListOf<ChoresItemDTO>()
        choresList3.add(ChoresItemDTO("2022-09-16", 0, "2022-09-20", "Nothing", 0, "pending"))


        val category1 = CategoryDTO(null, "#AED581", 0, "School")
        val category2 = CategoryDTO(null, "#EA80FC", 1, "Science")
        val category3 = CategoryDTO(null, "#FF7043", 1, "Sport")

        val listTasks = mutableListOf<AssignedTasksItemDTO>()
        listTasks.add(
            AssignedTasksItemDTO(
                25,
                "25-10-2022",
                "Home help",
                "Health",
                "2022-09-11",
                null,
                choresList,
                "Walk dog",
                0,
                0,
                3,
                0,
                category1,
                true,
                "2022-09-10",
                0,
                "pending"
            )
        )
        listTasks.add(
            AssignedTasksItemDTO(
                35,
                "25-10-2022",
                "Home help",
                "Study",
                "2022-09-09",
                null,
                choresList2,
                "Go to school",
                1,
                0,
                1,
                1,
                category2,
                true,
                "2022-09-09",
                0,
                "pending"
            )
        )

        listTasks.add(
            AssignedTasksItemDTO(
                40,
                "25-10-2022",
                "Home help",
                "Study",
                "2022-09-09",
                null,
                choresList3,
                "Go to shop",
                1,
                0,
                0,
                2,
                category3,
                true,
                "2022-09-09",
                0,
                "pending"
            )
        )

//        val list = leveltyDao.getTodayTask(kidName, date)
//        Log.d("MyLog", "list in taskStorageImpl = $listTasks")

        return listTasks
    }


    override fun getKidDetailTasksList(): List<TaskDTO> {
        val taskList = mutableListOf<TaskDTO>()
        val dateToday = "" //Calendar.getInstance().timeInMillis
        val startDate = "" //Calendar.getInstance().timeInMillis
        taskList.add(
            TaskDTO(
                0,
                "Find key",
                "Home",
                2,
                dateToday,
                startDate,
                "Weekly",
                "Order",
                "Finding scill",
                "Andrew",
                "Need approval"
            )
        )
        taskList.add(
            TaskDTO(
                1,
                "Walk dog",
                "Home",
                3,
                dateToday,
                startDate,
                "Weekly",
                "Order",
                "Animal scill",
                "Andrew",
                "Need approval"
            )
        )
        taskList.add(
            TaskDTO(
                2,
                "Find key",
                "Home",
                2,
                dateToday,
                startDate,
                "Weekly",
                "Order",
                "Finding scill",
                "Andrew",
                "Need approval"
            )
        )
        taskList.add(
            TaskDTO(
                3,
                "Walk dog",
                "Home",
                3,
                dateToday,
                startDate,
                "Weekly",
                "Order",
                "Animal scill",
                "Andrew",
                "Need approval"
            )
        )
        taskList.add(
            TaskDTO(
                4,
                "Find key",
                "Home",
                1,
                dateToday,
                startDate,
                "Weekly",
                "Order",
                "Finding scill",
                "Andrew",
                "Need approval"
            )
        )
        taskList.add(
            TaskDTO(
                5,
                "Walk dog",
                "Home",
                3,
                dateToday,
                startDate,
                "Weekly",
                "Order",
                "Animal scill",
                "Andrew",
                "Need approval"
            )
        )
        return taskList.toList()
    }

    override fun addTask(taskDTO: TaskDTO) {
        leveltyDao.insertTask(taskDTO)
    }

    override fun updateTask(taskDTO: TaskDTO) {
        leveltyDao.updateTask(taskDTO)
    }


}