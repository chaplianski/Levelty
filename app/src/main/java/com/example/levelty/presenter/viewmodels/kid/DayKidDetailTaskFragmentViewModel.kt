package com.example.levelty.presenter.viewmodels.kid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.ProcessedTask
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.usecases.kid.GetKidDetailTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DayKidDetailTaskFragmentViewModel  @Inject constructor(private val getKidDetailTasksUseCase: GetKidDetailTasksUseCase): ViewModel() {

    private val _taskList = MutableLiveData<List<CreatedTasksItem>>()
    val taskList: LiveData<List<CreatedTasksItem>> get() = _taskList
    private val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String> get() = _currentDay

    fun getTaskList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidDetailTasksUseCase.execute()
            val processedTask = mutableListOf<ProcessedTask>()
            for (task in list){
                if (task.isPeriodic == true){
//                    processedTask.add(ProcessedTask(task.id, task.title, task.cost, "", "", task.creatorId, task.category, task.assigneeId, task.status))
                }
            }
            _taskList.postValue(list)
        }
    }

//    fun getTaskList(){
//        viewModelScope.launch(Dispatchers.IO) {
//            val list = getKidDetailTasksUseCase.execute()
//            _taskList.postValue(list)
//        }
//    }

    fun transferDateValue(date: String){
        viewModelScope.launch(Dispatchers.IO) {
            _currentDay.postValue(date)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}