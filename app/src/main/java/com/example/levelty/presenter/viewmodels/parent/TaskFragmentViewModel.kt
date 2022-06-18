package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.usecases.parent.GetTasksFragmentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskFragmentViewModel @Inject constructor(private val getTasksFragmentUseCase: GetTasksFragmentUseCase): ViewModel() {

    val _tasksListValue = MutableLiveData<List<Task>>()
    val tasksListValue: LiveData<List<Task>> get() = _tasksListValue

    fun getTasksListValue(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getTasksFragmentUseCase.execute()
            _tasksListValue.postValue(list)
        }
    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}