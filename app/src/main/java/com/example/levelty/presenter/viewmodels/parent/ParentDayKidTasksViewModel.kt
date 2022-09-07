package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.usecases.parent.GetTasksUseCase
import com.example.levelty.domain.usecases.parent.UpdateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParentDayKidTasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    private val _allTaskList = MutableLiveData<List<CreatedTasksItem>>()
    val allTaskList: LiveData<List<CreatedTasksItem>> get() = _allTaskList
    private val _dayTaskList = MutableLiveData<List<CreatedTasksItem>>()
    val dayTaskList: LiveData<List<CreatedTasksItem>> = _dayTaskList
    val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String> get() = _currentDay


    fun getTasksList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getTasksUseCase.execute()
            _allTaskList.postValue(list)
        }
    }

    fun getDayTasks(tasks: List<CreatedTasksItem>, kidId: Int, date: String){

        val dayTasks = mutableListOf<CreatedTasksItem>()
        for (task in tasks) {
            if (task.assigneeId == kidId) {
                if (task.chores != null) {
                    for (chore in task.chores) {
                        if (chore?.date == date) {
                            dayTasks.add(task)
                        }
                    }
                }
            }
        }
        _dayTaskList.postValue(dayTasks)
    }

    fun transferDateValue(date: String){
        viewModelScope.launch(Dispatchers.IO) {
            _currentDay.postValue(date)
        }
    }

    fun updateTask (task: Task){
        viewModelScope.launch(Dispatchers.IO) {
//            Log.d("MyLog", "task = $task")
            updateTaskUseCase.execute(task)

        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

}