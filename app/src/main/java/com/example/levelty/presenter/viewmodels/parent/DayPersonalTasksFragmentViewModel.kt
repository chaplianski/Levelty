package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.usecases.parent.GetDayTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DayPersonalTasksFragmentViewModel @Inject constructor(
    private val getDayTasksUseCase: GetDayTasksUseCase
) : ViewModel() {

    val _dayTaskList = MutableLiveData<List<Task>>()
    val dayTaskList: LiveData<List<Task>> get() = _dayTaskList
    val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String> get() = _currentDay

    fun getDayTasks(kidName: String, date: String){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getDayTasksUseCase.execute(kidName, date)
            _dayTaskList.postValue(list)
        }
    }

    fun transferDateValue(date: String){
        viewModelScope.launch(Dispatchers.IO) {
            _currentDay.postValue(date)
        }
    }

}