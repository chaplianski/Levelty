package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.AddTaskUseCase
import com.example.levelty.presenter.viewmodels.parent.NewTaskViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class NewTaskViewModelFactory @Inject constructor(private val addTaskUseCase: AddTaskUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewTaskViewModel(addTaskUseCase) as T
    }

}