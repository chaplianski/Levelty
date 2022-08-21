package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.AddTaskUseCase
import com.example.levelty.domain.usecases.parent.GetKidsUseCase
import com.example.levelty.domain.usecases.parent.GetParentPurposesUseCase
import com.example.levelty.domain.usecases.parent.GetRepeatVariantsUseCase
import com.example.levelty.presenter.viewmodels.parent.NewTaskViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class NewTaskViewModelFactory @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getKidsUseCase: GetKidsUseCase,
    private val getParentPurposesUseCase: GetParentPurposesUseCase,
    private val getRepeatVariantsUseCase: GetRepeatVariantsUseCase
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewTaskViewModel(addTaskUseCase, getKidsUseCase, getParentPurposesUseCase, getRepeatVariantsUseCase) as T
    }

}