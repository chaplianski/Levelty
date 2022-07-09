package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.UpdateTaskUseCase
import com.example.levelty.presenter.viewmodels.parent.EditTaskFragmentViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class EditTaskFragmentViewModelFactory @Inject constructor(private val updateTaskUseCase: UpdateTaskUseCase): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditTaskFragmentViewModel (updateTaskUseCase) as T
    }
}