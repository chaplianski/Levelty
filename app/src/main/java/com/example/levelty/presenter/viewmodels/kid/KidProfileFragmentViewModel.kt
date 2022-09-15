package com.example.levelty.presenter.viewmodels.kid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.AssignedTasksItem
import com.example.levelty.domain.models.Child
import com.example.levelty.domain.models.KidProcessedTask
import com.example.levelty.domain.usecases.kid.GetChildUseCase
import com.example.levelty.presenter.utils.assignedTasksItemToProcessedTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidProfileFragmentViewModel @Inject constructor(
    private val getChildUseCase: GetChildUseCase
): ViewModel() {

    private val _child = MutableLiveData<Child>()
    val child: LiveData<Child> get() = _child
    private val _tasks = MutableLiveData<List<KidProcessedTask>>()
    val tasks: LiveData<List<KidProcessedTask>> get() = _tasks

    fun getChild(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = getChildUseCase.execute()
            _child.postValue(data)
        }
    }

    fun getTasksList(taskList: List<AssignedTasksItem?>?){
        val list = assignedTasksItemToProcessedTask(taskList)
        _tasks.postValue(list)
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

}