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
    private val getUpcomingTaskUseCase: GetTodayTasksUseCase,
    private val getKidInterestUseCase: GetKidInterestUseCase,
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val getParentsPurposeUseCase: GetParentsPurposeUseCase,
    private val getKidUseCase: GetKidUseCase
) : ViewModel() {

    val _kidsList = MutableLiveData<List<Kid>>()
    val kidList: LiveData<List<Kid>> get() = _kidsList
    val _uncomingTasksList = MutableLiveData<List<Task>>()
    val _kidValue = MutableLiveData<Kid>()
    val kidValue: LiveData<Kid> get() = _kidValue
    val uncommingTasksList: LiveData<List<Task>> get() = _uncomingTasksList
    val _kidInterestList = MutableLiveData<List<Interest>>()
    val kidInterestList: LiveData<List<Interest>> get() = _kidInterestList
    val _kidGoalsList = MutableLiveData<List<Goal>>()
    val kidGoalsList: LiveData<List<Goal>> get() = _kidGoalsList
    val _parantsPurposeList = MutableLiveData<List<Purpose>>()
    val parantsPurposeList: LiveData<List<Purpose>> get() = _parantsPurposeList


    fun getKids(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidsUseCase.execute()
            _kidsList.postValue(list)
        }
    }

    fun getKid(kidId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val currentKid = getKidUseCase.execute(kidId)
            _kidValue.postValue(currentKid)
        }
    }

    fun getTodayTasks(kidName: String, currentDate: String){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getUpcomingTaskUseCase.execute(kidName, currentDate)
//            Log.d("MyLog", "parametrs in profile view model = $kidName, $currentDate")
//            Log.d("MyLog", "list in profile view model = $list")
            _uncomingTasksList.postValue(list)
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