package com.example.levelty.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DateChooseFragmentViewModel : ViewModel() {

    val _yearValue = MutableLiveData<String>()
    val yearValue: LiveData<String> get() = _yearValue
    val _monthValue = MutableLiveData<String>()
    val monthValue: LiveData<String> get() = _monthValue
    val _dayValue = MutableLiveData<Int>()
    val dayValue: LiveData<Int> get() = _dayValue

    fun transferYearValue(year: String){
        viewModelScope.launch(Dispatchers.IO) {
           _yearValue.postValue(year)
        }
    }

    fun transferMonthValue(month: String){
        viewModelScope.launch(Dispatchers.IO) {
            _monthValue.postValue(month)
        }
    }

    fun transferDayValue(day: Int){
        viewModelScope.launch(Dispatchers.IO) {
           _dayValue.postValue(day)
        }
    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}