package com.example.levelty.presenter.viewmodels.parent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.ChildrenItem
import com.example.levelty.domain.models.NewTask
import com.example.levelty.domain.models.ParentProcessedTask
import com.example.levelty.domain.usecases.parent.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParentEditTaskFragmentViewModel @Inject constructor(
    private val updateParentTaskUseCase: UpdateParentTaskUseCase,
    private val getKidsUseCase: GetKidsUseCase,
    private val getRepeatVariantsForTaskUseCase: GetRepeatVariantsForTaskUseCase,
    private val getPointsVariantsForTaskUseCase: GetPointsVariantsForTaskUseCase,
    private val getParentPurposesUseCase: GetParentPurposesUseCase,
    ): ViewModel() {

    private val _kids = MutableLiveData<List<ChildrenItem>>()
    val kids: LiveData<List<ChildrenItem>> get() = _kids
    private val _purposes = MutableLiveData<List<String>>()
    val purpose: LiveData<List<String>> get() = _purposes
    private val _repeats = MutableLiveData<List<String>>()
    val repeats: LiveData<List<String>> get() = _repeats
    private val _dates = MutableLiveData<List<String>>()
    val dates: LiveData<List<String>> get() = _dates
    private val _points = MutableLiveData<List<String>>()
    val points: LiveData<List<String>> get() = _points
    private val _task = MutableLiveData<ParentProcessedTask>()
    val task: LiveData<ParentProcessedTask> get() = _task



    fun updateTask(taskId: Int, newTask: NewTask) {
        viewModelScope.launch(Dispatchers.IO) {
           Log.d("MyLog", "task = $newTask")
            updateParentTaskUseCase.execute(taskId, newTask)
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

    fun getPointsVariants(){
        viewModelScope.launch (Dispatchers.IO) {
            val pointList = getPointsVariantsForTaskUseCase.execute()
            _points.postValue(pointList)
        }

    }

    fun getRepeatVariants(){
        viewModelScope.launch (Dispatchers.IO) {
            val repeatList = getRepeatVariantsForTaskUseCase.execute()
            _repeats.postValue(repeatList)
        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}