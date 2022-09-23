package com.example.levelty.presenter.viewmodels.kid

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Goal
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.domain.usecases.kid.CompleteGoalUseCase
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidGoalsFragmentViewModel @Inject constructor(
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val completeGoalUseCase: CompleteGoalUseCase

    ): ViewModel() {

    private val _goals = MutableLiveData<List<GoalsItem>>()
    val goals: LiveData<List<GoalsItem>> = _goals

    fun getGoals(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidsGoalsUseCase.execute()
            _goals.postValue(list)
        }
    }

    fun completeGoal(idGoal: Int){
        viewModelScope.launch(Dispatchers.IO) {
            completeGoalUseCase.execute(idGoal)
            Log.d("MyLog", "Send idGoal = $idGoal")
        }

    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

    sealed class KidGoalsState() {
        data class Error(@StringRes val exception: Int) : KidGoalsState()
        data class Success(val ports: List<String>) : KidGoalsState()
        object Loading : KidGoalsState()
    }
}

