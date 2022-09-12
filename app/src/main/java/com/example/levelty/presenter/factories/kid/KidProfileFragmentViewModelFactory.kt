package com.example.levelty.presenter.factories.kid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.kid.GetChildUseCase
import com.example.levelty.presenter.viewmodels.kid.KidProfileFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class KidProfileFragmentViewModelFactory @Inject constructor(
    private val getChildUseCase: GetChildUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KidProfileFragmentViewModel(getChildUseCase) as T
    }
}