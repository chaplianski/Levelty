package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Interest
import com.example.levelty.domain.usecases.parent.GetKidsInterestChooseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidsInterestChooseFragmentViewModel @Inject constructor(
    private val getKidsInterestChooseUseCase: GetKidsInterestChooseUseCase
): ViewModel() {

    val _interestList = MutableLiveData<List<Interest>>()
    val interestList: LiveData<List<Interest>> get() = _interestList

    fun getInterestVariants(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getKidsInterestChooseUseCase.execute()
            _interestList.postValue(list)
        }

    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}