package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Kid
import com.example.levelty.domain.models.Purpose
import com.example.levelty.domain.models.Repeat
import com.example.levelty.domain.models.Task
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

    val _kids = MutableLiveData<List<Kid>>()
    val kids: LiveData<List<Kid>> get() = _kids
    val _purposes = MutableLiveData<List<Purpose>>()
    val purpose: LiveData<List<Purpose>> get() = _purposes
    val _repeats = MutableLiveData<List<Repeat>>()
    val repeats: LiveData<List<Repeat>> get() = _repeats

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

    fun getRepeatVariants(){
        viewModelScope.launch(Dispatchers.IO) {
            val repeats = getRepeatVariantsUseCase.execute()
            _repeats.postValue(repeats)
        }
    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}