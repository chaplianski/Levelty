package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetTasksUseCase
import com.example.levelty.presenter.viewmodels.parent.TaskFragmentViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class TaskFragmentViewModelFactory @Inject constructor(private val getTasksUseCase: GetTasksUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskFragmentViewModel(getTasksUseCase) as T
    }

}