package com.example.levelty.presenter.viewmodels.parent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Task
import com.example.levelty.domain.usecases.parent.UpdateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditTaskFragmentViewModel @Inject constructor(private val updateTaskUseCase: UpdateTaskUseCase): ViewModel() {

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
           Log.d("MyLog", "task = $task")
            updateTaskUseCase.execute(task)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}