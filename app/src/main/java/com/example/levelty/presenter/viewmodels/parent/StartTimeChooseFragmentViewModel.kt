package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class StartTimeChooseFragmentViewModel: ViewModel() {

    val _hourValue = MutableLiveData<String>()
    val hourValue: LiveData<String> get() = _hourValue
    val _minuteValue = MutableLiveData<String>()
    val minuteValue: LiveData<String> get() = _minuteValue
    val _amPmValue = MutableLiveData<String>()
    val amPmValue: LiveData<String> get() = _amPmValue

    fun transferHourValue(hour: String){
        viewModelScope.launch(Dispatchers.IO) {
            _hourValue.postValue(hour)
        }
    }

    fun transferMinuteValue(minute: String){
        viewModelScope.launch(Dispatchers.IO) {
            _minuteValue.postValue(minute)
        }
    }

    fun transferAmPmValue(amPm: String){
        viewModelScope.launch(Dispatchers.IO) {
           _amPmValue.postValue(amPm)
        }
    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}