package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Purpose
import com.example.levelty.domain.usecases.parent.GetParentPurposesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ParentPurposeChooseViewModel @Inject constructor(private val getParentPurposesUseCase: GetParentPurposesUseCase): ViewModel() {

    val _purposeList = MutableLiveData<List<Purpose>>()
    val purposeList: LiveData<List<Purpose>> get() = _purposeList

    fun getPurposeVariants(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getParentPurposesUseCase.execute()
            _purposeList.postValue(list)
        }

    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}