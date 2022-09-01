package com.example.levelty.presenter.viewmodels.kid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidGoalsFragmentViewModel @Inject constructor(private val getKidsGoalsUseCase: GetKidsGoalsUseCase): ViewModel() {

    private val _goals = MutableLiveData<List<GoalsItem>>()
    val goals: LiveData<List<GoalsItem>> = _goals

    fun getGoals(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidsGoalsUseCase.execute()
            _goals.postValue(list)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}