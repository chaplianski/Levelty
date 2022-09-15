package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.ParentProcessedTask
import com.example.levelty.domain.usecases.parent.GetTasksUseCase
import com.example.levelty.domain.usecases.parent.UpdateTaskStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParentDayKidTasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskStatusUseCase: UpdateTaskStatusUseCase
) : ViewModel() {

    private val _allTaskList = MutableLiveData<List<ParentProcessedTask>>()
    val allTaskList: LiveData<List<ParentProcessedTask>> get() = _allTaskList
    private val _dayTaskList = MutableLiveData<List<ParentProcessedTask>>()
    val dayTaskList: LiveData<List<ParentProcessedTask>> = _dayTaskList
    val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String> get() = _currentDay


    fun getTasksList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getTasksUseCase.execute()
            _allTaskList.postValue(list)
        }
    }

    fun getDayTasks(tasks: List<ParentProcessedTask>, kidId: Int, date: String) {
        val dayTasks = mutableListOf<ParentProcessedTask>()
        for (task in tasks) {
            if (task.choreDate == date && task.assigneeId == kidId)
                dayTasks.add(task)
        }
        _dayTaskList.postValue(dayTasks)
    }

    fun transferDateValue(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentDay.postValue(date)
        }
    }

    fun updateTask(taskId: Int, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskStatusUseCase.execute(taskId, status)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

}