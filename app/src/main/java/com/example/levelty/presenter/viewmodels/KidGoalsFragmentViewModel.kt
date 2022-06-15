package com.example.levelty.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.usecases.GetKidGoalsFragmentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidGoalsFragmentViewModel @Inject constructor(private val getKidGoalsFragmentUseCase: GetKidGoalsFragmentUseCase): ViewModel() {

    val _goalsValue = MutableLiveData<List<Goal>>()
    val goalsValue: LiveData<List<Goal>> get() = _goalsValue

    fun getGoalsList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidGoalsFragmentUseCase.execute()
            _goalsValue.postValue(list)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}