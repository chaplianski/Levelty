package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.*
import com.example.levelty.domain.usecases.parent.AddTaskUseCase
import com.example.levelty.domain.usecases.parent.GetKidsUseCase
import com.example.levelty.domain.usecases.parent.GetParentPurposesUseCase
import com.example.levelty.domain.usecases.parent.GetRepeatVariantsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getKidsUseCase: GetKidsUseCase,
    private val getParentPurposesUseCase: GetParentPurposesUseCase,
    private val getRepeatVariantsUseCase: GetRepeatVariantsUseCase


    ): ViewModel() {

    private val _kids = MutableLiveData<List<ChildrenItem>>()
    val kids: LiveData<List<ChildrenItem>> get() = _kids
    private val _purposes = MutableLiveData<List<Purpose>>()
    val purpose: LiveData<List<Purpose>> get() = _purposes
    private val _repeats = MutableLiveData<List<String>>()
    val repeats: LiveData<List<String>> get() = _repeats
    private val _dates = MutableLiveData<List<String>>()
    val dates: LiveData<List<String>> get() = _dates
    private val _points = MutableLiveData<List<String>>()
    val points: LiveData<List<String>> get() = _points

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase.execute(task)
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
        val dataList = listOf<String>("Today", "Tomorrow", "Set another day")
        _dates.postValue(dataList)
    }

    fun getPointsVariants(){
        val pointList = listOf<String>("5", "15", "35", "55", "80", "Custom")
        _points.postValue(pointList)
    }

    fun getRepeatVariants(){
        val repeatList = listOf("Don't repeat", "Daily", "Every 3 days", "Every week", "Every month", "Custom")
        _repeats.postValue(repeatList)
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}