package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.ParentProcessedTask
import com.example.levelty.domain.usecases.parent.GetTasksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryFragmentViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
): ViewModel() {

    private val _tasks = MutableLiveData<List<ParentProcessedTask>>()
    val tasks: LiveData<List<ParentProcessedTask>> get() = _tasks

    fun getTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = getTasksUseCase.execute()
            _tasks.postValue(tasks)
        }
    }

}