package com.example.levelty.presenter.factories.parent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.levelty.domain.usecases.parent.GetKidsInterestChooseUseCase
import com.example.levelty.presenter.viewmodels.parent.KidsInterestChooseFragmentViewModel
import javax.inject.Inject

@Suppress ("UNCHECKED_CAST")
class KidsInterestChooseFragmentViewModelFactory @Inject constructor(
    private val getKidsInterestChooseUseCase: GetKidsInterestChooseUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KidsInterestChooseFragmentViewModel(getKidsInterestChooseUseCase) as T
    }
}