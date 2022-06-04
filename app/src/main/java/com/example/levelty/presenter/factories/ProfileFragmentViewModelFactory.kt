package com.example.levelty.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.*
import com.example.levelty.presenter.viewmodels.ProfileFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProfileFragmentViewModelFactory @Inject constructor(
    private val getKidsUseCase: GtKidsUseCase,
    private val getDayTaskUseCase: GetDayTaskUseCase,
    private val getKidInterestUseCase: GetKidInterestUseCase,
    private val getKidsGoalsUseCase: GetKidsGoalsUseCase,
    private val getParentsPurposeUseCase: GetParentsPurposeUseCase

): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileFragmentViewModel(
            getKidsUseCase,
            getDayTaskUseCase,
            getKidInterestUseCase,
            getKidsGoalsUseCase,
            getParentsPurposeUseCase
        ) as T
    }
}