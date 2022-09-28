package com.example.levelty.presenter.viewmodels.parent

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.exceptions.InternetConnectionException
import com.example.levelty.domain.exceptions.NetworkException
import com.example.levelty.domain.models.GoalsItem
import com.example.levelty.domain.usecases.parent.ApproveGoalUseCase
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParentKidGoalsFragmentViewModel @Inject constructor(
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val approveGoalUseCase: ApproveGoalUseCase
    ): ViewModel() {

    private val _goalsValue = MutableLiveData<List<GoalsItem>>()
    val goalsValue: LiveData<List<GoalsItem>> get() = _goalsValue

    private val _goalStateData = MutableStateFlow<ParentKidGoalState>(ParentKidGoalState.Loading)
    val goalStateData = _goalStateData.asStateFlow()

    fun getGoalsList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidsGoalsUseCase.execute()
            _goalsValue.postValue(list)
        }
    }

    suspend fun approveGoal(goalId: Int, goalPrice: Int){
        approveGoalUseCase.execute(goalId, goalPrice).fold({
            _goalStateData.emit(ParentKidGoalState.Success(it))
        }, {
            when (it){
                is NetworkException -> {
                    _goalStateData.emit(ParentKidGoalState.Error(it.errorMessage))
                }
                is InternetConnectionException -> {
                    _goalStateData.emit(ParentKidGoalState.Error(it.errorMessage))
                }
                else -> {}
            }
        })
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}

sealed class ParentKidGoalState(){
    object Loading: ParentKidGoalState()
    data class Success(val goalItem: GoalsItem): ParentKidGoalState()
    data class Error(@StringRes val exception: Int): ParentKidGoalState()
}