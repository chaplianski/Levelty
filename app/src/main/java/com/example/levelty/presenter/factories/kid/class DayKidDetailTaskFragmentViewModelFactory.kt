package com.example.levelty.presenter.factories.kid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.kid.GetKidDetailTasksUseCase
import com.example.levelty.presenter.viewmodels.kid.DayKidDetailTaskFragmentViewModel
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class DayKidDetailTaskFragmentViewModelFactory  @Inject constructor(
    private val getKidDetailTasksUseCase: GetKidDetailTasksUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DayKidDetailTaskFragmentViewModel(getKidDetailTasksUseCase) as T
    }
}