package com.example.levelty.presenter.viewmodels.kid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidGoalsFragmentViewModel @Inject constructor(private val getKidsGoalsUseCase: GetKidsGoalsUseCase): ViewModel() {

    private val _goals = MutableLiveData<List<Goal>>()
    val goals: LiveData<List<Goal>> = _goals

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