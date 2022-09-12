package com.example.levelty.presenter.viewmodels.kid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.models.Child
import com.example.levelty.domain.usecases.kid.GetChildUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class KidProfileFragmentViewModel @Inject constructor(
    private val getChildUseCase: GetChildUseCase
): ViewModel() {

    private val _child = MutableLiveData<Child>()
    val child: LiveData<Child> get() = _child

    fun getChild(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = getChildUseCase.execute()
            _child.postValue(data)
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }

}