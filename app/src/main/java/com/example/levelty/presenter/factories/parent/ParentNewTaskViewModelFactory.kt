package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.*
import com.example.levelty.presenter.viewmodels.parent.ParentNewTaskViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ParentNewTaskViewModelFactory @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getKidsUseCase: GetKidsUseCase,
    private val getParentPurposesUseCase: GetParentPurposesUseCase,
//    private val getRepeatVariantsUseCase: GetRepeatVariantsUseCase,
    private val getTasksUseCase: GetTasksUseCase,
    private val getDateVariantsForTaskUseCase: GetDateVariantsForTaskUseCase,
    private val getPointsVariantsForTaskUseCase: GetPointsVariantsForTaskUseCase,
    private val getRepeatVariantsForTaskUseCase: GetRepeatVariantsForTaskUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ParentNewTaskViewModel(
            addTaskUseCase,
            getKidsUseCase,
            getParentPurposesUseCase,
            getTasksUseCase,
            getDateVariantsForTaskUseCase,
            getPointsVariantsForTaskUseCase,
            getRepeatVariantsForTaskUseCase
        ) as T
    }

}