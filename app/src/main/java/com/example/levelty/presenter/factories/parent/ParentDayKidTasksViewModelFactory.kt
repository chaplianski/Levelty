package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetDayTasksUseCase
import com.example.levelty.domain.usecases.parent.GetTasksUseCase
import com.example.levelty.domain.usecases.parent.UpdateTaskUseCase
import com.example.levelty.presenter.viewmodels.parent.ParentDayKidTasksViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ParentDayKidTasksViewModelFactory @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ParentDayKidTasksViewModel(getTasksUseCase, updateTaskUseCase) as T
    }

}