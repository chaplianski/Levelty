package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.*
import com.example.levelty.presenter.viewmodels.parent.ParentEditTaskFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ParentEditTaskFragmentViewModelFactory @Inject constructor(
    private val updateParentTaskUseCase: UpdateParentTaskUseCase,
    private val getKidsUseCase: GetKidsUseCase,
    private val getRepeatVariantsForTaskUseCase: GetRepeatVariantsForTaskUseCase,
    private val getPointsVariantsForTaskUseCase: GetPointsVariantsForTaskUseCase,
    private val getParentPurposesUseCase: GetParentPurposesUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ParentEditTaskFragmentViewModel(
            updateParentTaskUseCase,
            getKidsUseCase,
            getRepeatVariantsForTaskUseCase,
            getPointsVariantsForTaskUseCase,
            getParentPurposesUseCase
        ) as T
    }
}