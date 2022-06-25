package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.usecases.parent.AddTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewTaskViewModel @Inject constructor(private val addTaskUseCase: AddTaskUseCase): ViewModel() {

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase.execute(task)
        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}