package com.example.levelty.presenter.viewmodels.kid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.KidProcessedTask
import com.example.levelty.domain.models.ParentProcessedTask
import com.example.levelty.domain.usecases.kid.GetKidDetailTasksUseCase
import com.example.levelty.presenter.utils.dateShortStringToTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidDayTaskFragmentViewModel  @Inject constructor(private val getKidDetailTasksUseCase: GetKidDetailTasksUseCase): ViewModel() {

    private val _taskList = MutableLiveData<List<KidProcessedTask>>()
    val taskList: LiveData<List<KidProcessedTask>> get() = _taskList
    private val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String> get() = _currentDay
    private val _todayTasksList = MutableLiveData<List<KidProcessedTask>>()
    val todayTasksList: LiveData<List<KidProcessedTask>> get() = _todayTasksList

    fun getTaskList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidDetailTasksUseCase.execute()
           _taskList.postValue(list)
        }
    }

    fun getDayTaskList(date: String, taskList: List<KidProcessedTask>){
        val daysTasks = mutableListOf<KidProcessedTask>()
        for (task in taskList){
//            Log.d("MyLog", "taskDate = ${task.choreDate}, date = $date")

            if (task.choreDate?.let { dateShortStringToTime(it) } == dateShortStringToTime(date)){
//                for (i in task.choreDate?.toCharArray()?.indices!!){
//                    Log.d("MyLog","symbols ${task.choreDate.toCharArray()[i]} and ${date.toCharArray()[i]} = ${task.choreDate.toCharArray()[i] == date.toCharArray()[i]}")
//                }
//                if (task.choreDate?.toCharArray()?.equals(date.toCharArray()) == true){
                daysTasks.add(task)
//                Log.d("MyLog", "task = $task")
            }
        }
        _todayTasksList.postValue(daysTasks)
    }

//    fun getTaskList(){
//        viewModelScope.launch(Dispatchers.IO) {
//            val list = getKidDetailTasksUseCase.execute()
//            _taskList.postValue(list)
//        }
//    }

    fun transferDateValue(date: String){
        viewModelScope.launch(Dispatchers.IO) {
           Log.d("MyLog", "date vm = $date")
            _currentDay.postValue(date)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}