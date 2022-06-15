package com.example.levelty.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.GetTasksFragmentUseCase
import com.example.levelty.presenter.viewmodels.TaskFragmentViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class TaskFragmentViewModelFactory @Inject constructor(private val getTasksFragmentUseCase: GetTasksFragmentUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskFragmentViewModel(getTasksFragmentUseCase) as T
    }

}