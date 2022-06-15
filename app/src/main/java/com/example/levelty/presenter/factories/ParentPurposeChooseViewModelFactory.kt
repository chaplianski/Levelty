package com.example.levelty.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.GetParentPurposesUseCase
import com.example.levelty.presenter.viewmodels.ParentPurposeChooseViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class ParentPurposeChooseViewModelFactory @Inject constructor(private val getParentPurposesUseCase: GetParentPurposesUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ParentPurposeChooseViewModel(getParentPurposesUseCase) as T
    }
}