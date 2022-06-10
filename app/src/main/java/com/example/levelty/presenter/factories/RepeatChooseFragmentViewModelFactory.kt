package com.example.levelty.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.GetRepeatVariantsUseCase
import com.example.levelty.presenter.viewmodels.RepeatChooseFragmentViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class RepeatChooseFragmentViewModelFactory @Inject constructor(private val getRepeatVariansUseCase: GetRepeatVariantsUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepeatChooseFragmentViewModel(getRepeatVariansUseCase) as T
    }
}