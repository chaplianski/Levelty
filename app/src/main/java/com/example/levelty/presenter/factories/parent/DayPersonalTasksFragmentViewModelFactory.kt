package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetDayTasksUseCase
import com.example.levelty.presenter.viewmodels.parent.DayPersonalTasksFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class DayPersonalTasksFragmentViewModelFactory @Inject constructor(
    private val getDayTasksUseCase: GetDayTasksUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DayPersonalTasksFragmentViewModel(getDayTasksUseCase) as T
    }

}