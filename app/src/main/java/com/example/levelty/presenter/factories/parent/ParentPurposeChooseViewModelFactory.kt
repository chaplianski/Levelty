package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetParentPurposesUseCase
import com.example.levelty.presenter.viewmodels.parent.ParentPurposeChooseViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class ParentPurposeChooseViewModelFactory @Inject constructor(private val getParentPurposesUseCase: GetParentPurposesUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ParentPurposeChooseViewModel(getParentPurposesUseCase) as T
    }
}