package com.example.levelty.presenter.factories.kid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.kid.GetKidDetailTasksUseCase
import com.example.levelty.domain.usecases.kid.UpdateChoreStatusUseCase
import com.example.levelty.presenter.viewmodels.kid.KidDayTaskFragmentViewModel
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class KidDayTaskFragmentViewModelFactory  @Inject constructor(
    private val getKidDetailTasksUseCase: GetKidDetailTasksUseCase,
    private val updateChoreStatusUseCase: UpdateChoreStatusUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KidDayTaskFragmentViewModel(getKidDetailTasksUseCase, updateChoreStatusUseCase) as T
    }
}