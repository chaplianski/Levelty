package com.example.levelty.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.*
import com.example.levelty.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragmentViewModel @Inject constructor(
    private val getKidsUseCase: GtKidsUseCase,
    private val getIncomingTaskUseCase: GetDayTaskUseCase,
    private val getKidInterestUseCase: GetKidInterestUseCase,
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val getParentsPurposeUseCase: GetParentsPurposeUseCase
) : ViewModel() {

    val _kidsList = MutableLiveData<List<Kid>>()
    val answersList: LiveData<List<Kid>> get() = _kidsList
    val _uncomingTasksList = MutableLiveData<List<Task>>()
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

    fun getUncomingTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getIncomingTaskUseCase.execute()
            _uncomingTasksList.postValue(list)
        }
    }

    fun getInterests(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidInterestUseCase.execute()
            _kidInterestList.postValue(list)
        }
    }

    fun getGoals(){
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