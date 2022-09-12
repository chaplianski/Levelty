package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.*
import com.example.levelty.domain.usecases.parent.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getKidsUseCase: GetKidsUseCase,
    private val getParentPurposesUseCase: GetParentPurposesUseCase,
//    private val getRepeatVariantsUseCase: GetRepeatVariantsUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val getDateVariantsForTaskUseCase: GetDateVariantsForTaskUseCase,
    private val getPointsVariantsForTaskUseCase: GetPointsVariantsForTaskUseCase,
    private val getRepeatVariantsForTaskUseCase: GetRepeatVariantsForTaskUseCase


    ): ViewModel() {

    private val _kids = MutableLiveData<List<ChildrenItem>>()
    val kids: LiveData<List<ChildrenItem>> get() = _kids
    private val _purposes = MutableLiveData<List<String>>()
    val purpose: LiveData<List<String>> get() = _purposes
    private val _repeats = MutableLiveData<List<String>>()
    val repeats: LiveData<List<String>> get() = _repeats
    private val _dates = MutableLiveData<List<String>>()
    val dates: LiveData<List<String>> get() = _dates
    private val _points = MutableLiveData<List<String>>()
    val points: LiveData<List<String>> get() = _points
    private val _task = MutableLiveData<ProcessedTask>()
    val task: LiveData<ProcessedTask> get() = _task

    fun addTask(newTask: NewTask){
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase.execute(newTask)
        }
    }

    fun getKids(){
        viewModelScope.launch(Dispatchers.IO) {
            val kids = getKidsUseCase.execute()
            _kids.postValue(kids)
        }
    }

    fun getPurpose(){
        viewModelScope.launch(Dispatchers.IO) {
            val purposes = getParentPurposesUseCase.execute()
            _purposes.postValue(purposes)
        }
    }

//    fun getRepeatVariants(){
//        viewModelScope.launch(Dispatchers.IO) {
//            val repeats = getRepeatVariantsUseCase.execute()
//            _repeats.postValue(repeats)
//        }
//    }

    fun getDateVariants(){
        viewModelScope.launch (Dispatchers.IO) {
            val dataList = getDateVariantsForTaskUseCase.execute()
            _dates.postValue(dataList)
        }
    }

    fun getPointsVariants(){
        viewModelScope.launch (Dispatchers.IO) {
            val pointList = getPointsVariantsForTaskUseCase.execute()
            _points.postValue(pointList)
        }

    }

    fun getRepeatVariants(){
        viewModelScope.launch (Dispatchers.IO) {
            val repeatList = getRepeatVariantsForTaskUseCase.execute()
            _repeats.postValue(repeatList)
        }

    }

    fun getCurrentTask(taskId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val allTasks = getTasksUseCase.execute()
            for (task in allTasks){
                if (task.id == taskId) _task.postValue(task)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}