package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.usecases.parent.GetKidGoalsFragmentUseCase
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParentKidGoalsFragmentViewModel @Inject constructor(private val getKidsGoalsUseCase: GetKidsGoalsUseCase): ViewModel() {

    private val _goalsValue = MutableLiveData<List<Goal>>()
    val goalsValue: LiveData<List<Goal>> get() = _goalsValue

    fun getGoalsList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidsGoalsUseCase.execute()
            _goalsValue.postValue(list)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}