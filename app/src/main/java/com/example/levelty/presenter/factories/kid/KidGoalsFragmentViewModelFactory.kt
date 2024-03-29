package com.example.levelty.presenter.factories.kid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.kid.CompleteGoalUseCase
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import com.example.levelty.presenter.viewmodels.kid.KidGoalsFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class KidGoalsFragmentViewModelFactory @Inject constructor(
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val completeGoalUseCase: CompleteGoalUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KidGoalsFragmentViewModel(getKidsGoalsUseCase, completeGoalUseCase) as T
    }
}