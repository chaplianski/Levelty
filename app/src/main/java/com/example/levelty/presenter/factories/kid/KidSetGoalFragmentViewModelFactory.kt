package com.example.levelty.presenter.factories.kid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import com.example.levelty.presenter.viewmodels.kid.KidSetGoalFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class KidSetGoalFragmentViewModelFactory @Inject constructor(
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KidSetGoalFragmentViewModel(getKidsGoalsUseCase) as T
    }
}