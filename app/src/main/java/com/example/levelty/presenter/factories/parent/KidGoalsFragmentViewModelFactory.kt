package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetKidGoalsFragmentUseCase
import com.example.levelty.presenter.viewmodels.parent.KidGoalsFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class KidGoalsFragmentViewModelFactory @Inject constructor(private val getKidGoalsFragmentUseCase: GetKidGoalsFragmentUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KidGoalsFragmentViewModel(getKidGoalsFragmentUseCase) as T
    }
}