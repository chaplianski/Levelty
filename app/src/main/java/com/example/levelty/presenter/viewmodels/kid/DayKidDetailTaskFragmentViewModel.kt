package com.example.levelty.presenter.viewmodels.kid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.usecases.kid.GetKidDetailTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DayKidDetailTaskFragmentViewModel  @Inject constructor(private val getKidDetailTasksUseCase: GetKidDetailTasksUseCase): ViewModel() {

    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>> get() = _taskList
    private val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String> get() = _currentDay

    fun getTaskList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidDetailTasksUseCase.execute()
            _taskList.postValue(list)
        }
    }

    fun transferDateValue(date: String){
        viewModelScope.launch(Dispatchers.IO) {
            _currentDay.postValue(date)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}