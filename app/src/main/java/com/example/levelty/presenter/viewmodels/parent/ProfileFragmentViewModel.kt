package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.*
import com.example.levelty.domain.usecases.parent.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragmentViewModel @Inject constructor(
    private val getKidsUseCase: GetKidsUseCase,
    private val getTasksUseCase: GetTasksUseCase,
//    private val getTodayTasksUseCase: GetTodayTasksUseCase,
    private val getKidInterestUseCase: GetKidInterestUseCase,
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val getParentsPurposeUseCase: GetParentsPurposeUseCase,
    private val getKidUseCase: GetKidUseCase
) : ViewModel() {

    private val _kidsList = MutableLiveData<List<ChildrenItem>>()
    val kidList: LiveData<List<ChildrenItem>> get() = _kidsList
    private val _todayTaskList = MutableLiveData<List<ParentProcessedTask>>()
    val todayTasksList: LiveData<List<ParentProcessedTask>> get() = _todayTaskList
    private val _kidValue = MutableLiveData<ChildrenItem>()
    val kidValue: LiveData<ChildrenItem> get() = _kidValue
    val _kidInterestList = MutableLiveData<List<Interest>>()
    val kidInterestList: LiveData<List<Interest>> get() = _kidInterestList
    val _kidGoalsList = MutableLiveData<List<GoalsItem>>()
    val kidGoalsList: LiveData<List<GoalsItem>> get() = _kidGoalsList
    val _parantsPurposeList = MutableLiveData<List<Purpose>>()
    val parantsPurposeList: LiveData<List<Purpose>> get() = _parantsPurposeList


    fun getKids(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidsUseCase.execute()
            _kidsList.postValue(list)
        }
    }

    fun getKid(kidId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val currentKid = getKidUseCase.execute(kidId)
            _kidValue.postValue(currentKid)
        }
    }

    fun getTodayTasks(kidId: Int){
        viewModelScope.launch(Dispatchers.IO) {

            val allTasksList = getTasksUseCase.execute()
//            val allTasksList = getTasksUseCase.execute()
//            val todayTasks = mutableListOf<CreatedTasksItem>()
//
//            val currentDate = getTodayDateMls()
//
//            for (task in allTasksList){
//                if (kidId == task.assigneeId){
//
//                    val startDate = task.startDate?.let { dateShortStringToTime(it) }
//                    val endDate = task.dueDate?.let { dateShortStringToTime(it) }
//                    val repeat = task.repeatInterval?.let { dayToMls(it) }
//                    Log.d("MyLog", "start = $startDate, end = $endDate, current = $currentDate")
//                    if (currentDate != null) {
//                        if (currentDate < endDate!!){
//                            if (startDate == currentDate || ((currentDate - startDate!!) % repeat!!) == 0L){
//                                todayTasks.add(task)
//                            }
//                        }
//                    }
//
//                }
//            }

            _todayTaskList.postValue(allTasksList)
        }
    }

    fun getInterests(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidInterestUseCase.execute()
            _kidInterestList.postValue(list)
        }
    }

    fun getTodayGoals(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidsGoalsUseCase.execute()
            _kidGoalsList.postValue(list)
        }
    }

    fun getParentsPurpose(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getParentsPurposeUseCase.execute()
            _parantsPurposeList.postValue(list)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}