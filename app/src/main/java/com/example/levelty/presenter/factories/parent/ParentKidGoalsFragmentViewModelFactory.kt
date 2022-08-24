package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetKidsGoalsUseCase
import com.example.levelty.presenter.viewmodels.kid.KidGoalsFragmentViewModel
import com.example.levelty.presenter.viewmodels.parent.ParentKidGoalsFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ParentKidGoalsFragmentViewModelFactory @Inject constructor(private val getKidsGoalsUseCase: GetKidsGoalsUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ParentKidGoalsFragmentViewModel(getKidsGoalsUseCase) as T
    }
}