package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Repeat
import com.example.levelty.domain.usecases.parent.GetRepeatVariantsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

//class RepeatChooseFragmentViewModel(private val getRepeatVariansUseCase: GetRepeatVariantsUseCase) : ViewModel() {
//
//    val _repeatList = MutableLiveData<List<Repeat>>()
//    val repeatList: LiveData<List<Repeat>> get() = _repeatList
//
//    fun getRepeatVariants(){
//        viewModelScope.launch(Dispatchers.IO) {
//            val list = getRepeatVariansUseCase.execute()
//            _repeatList.postValue(list)
//        }
//
//    }
//
//
//    override fun onCleared() {
//        viewModelScope.cancel()
//    }
//}