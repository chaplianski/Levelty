package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.*
import com.example.levelty.presenter.viewmodels.parent.ParentProfileFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ParentProfileFragmentViewModelFactory @Inject constructor(
    private val getKidsUseCase: GetKidsUseCase,
    private val getTasksUseCase: GetTasksUseCase,
//    private val getTodayTasksUseCase: GetTodayTasksUseCase,
    private val getKidInterestUseCase: GetKidInterestUseCase,
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val getParentsPurposeUseCase: GetParentsPurposeUseCase,
//    private val getKidUseCase: GetKidUseCase

): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ParentProfileFragmentViewModel(
            getKidsUseCase,
            getTasksUseCase,
            getKidInterestUseCase,
            getKidsGoalsUseCase,
            getParentsPurposeUseCase,
//            getKidUseCase
        ) as T
    }
}