package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetTasksFragmentUseCase
import com.example.levelty.presenter.viewmodels.parent.TaskFragmentViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class TaskFragmentViewModelFactory @Inject constructor(private val getTasksFragmentUseCase: GetTasksFragmentUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskFragmentViewModel(getTasksFragmentUseCase) as T
    }

}