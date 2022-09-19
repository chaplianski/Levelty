package com.example.levelty.presenter.viewmodels.kid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidSetGoalFragmentViewModel @Inject constructor(
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase
): ViewModel() {

    private val _goals = MutableLiveData<List<GoalsItem>>()
    val goals: LiveData<List<GoalsItem>> get() = _goals

    fun getGoals () {
        val list = getKidsGoalsUseCase.execute()
        _goals.postValue(list)
    }

    fun sendNewGoalToParent(goal: String){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("MyLog", "sending goal = $goal")
        }
    }

    fun confirmGoal(goal: GoalsItem){
        viewModelScope.launch {
            Log.d("MyLog", "confimed goal title = ${goal.title}")
        }
    }

}