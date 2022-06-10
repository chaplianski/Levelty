package com.example.levelty.presenter.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.GetCategoriesUseCase
import com.example.levelty.presenter.viewmodels.CategoryChooseViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class CategoryChooseViewModelFactory @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryChooseViewModel(getCategoriesUseCase) as T
    }
}