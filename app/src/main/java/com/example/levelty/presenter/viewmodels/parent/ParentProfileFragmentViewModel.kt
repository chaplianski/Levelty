package com.example.levelty.presenter.viewmodels.parent

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.exceptions.InternetConnectionException
import com.example.levelty.domain.exceptions.NetworkException
import com.example.levelty.domain.models.*
import com.example.levelty.domain.usecases.parent.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParentProfileFragmentViewModel @Inject constructor(
    private val getKidsUseCase: GetKidsUseCase,
    private val getTasksUseCase: GetTasksUseCase,
//    private val getTodayTasksUseCase: GetTodayTasksUseCase,
    private val getKidInterestUseCase: GetKidInterestUseCase,
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val getParentsPurposeUseCase: GetParentsPurposeUseCase,
//    private val getKidUseCase: GetKidUseCase
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

//    private val screenStateData = MutableStateFlow<ParentProfileState>(
//        ParentProfileState.Loading
//    )
//    val screenState = screenStateData.asStateFlow()
//
//    suspend fun getKids() {
//        getKidsUseCase.execute().fold({
//            screenStateData.emit(ParentProfileState.Success(it))
//        }, {
//            when (it) {
//                is NetworkException -> {
//                    screenStateData.emit(ParentProfileState.Error(it.errorMessage))
//                }
//                is InternetConnectionException -> {
//                    screenStateData.emit(ParentProfileState.Error(it.errorMessage))
//                }
//                else -> {}
//            }
//        })
//    }


//    fun getKid(kidId: Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            val currentKid = getKidUseCase.execute(kidId)
//            _kidValue.postValue(currentKid)
//        }
//    }

    fun getTodayTasks(kidId: Int){
        viewModelScope.launch(Dispatchers.IO) {

            val allTasksList = getTasksUseCase.execute()
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

    sealed class ParentProfileState {
        data class Error (@StringRes val exception: Int): ParentProfileState()
        data class SuccessKids (val kidList: List<ChildrenItem>): ParentProfileState()
        data class SuccessTasks (val kidList: List<ParentProcessedTask>): ParentProfileState()
        object Loading: ParentProfileState()
    }
}

