package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.CreatedTasksItem
import com.example.levelty.domain.models.ProcessedTask
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.usecases.parent.GetTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskFragmentViewModel @Inject constructor(private val getTasksUseCase: GetTasksUseCase): ViewModel() {

    private val _tasksListValue = MutableLiveData<List<ProcessedTask>>()
    val tasksListValue: LiveData<List<ProcessedTask>> get() = _tasksListValue

    fun getTasksListValue(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getTasksUseCase.execute()
            _tasksListValue.postValue(list)
        }
    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}