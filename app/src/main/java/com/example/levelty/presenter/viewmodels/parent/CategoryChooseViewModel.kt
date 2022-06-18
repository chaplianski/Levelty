package com.example.levelty.presenter.viewmodels.parent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelty.domain.usecases.parent.GetCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryChooseViewModel @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase) : ViewModel() {

    val _categoryList = MutableLiveData<List<String>>()
    val categoryList: LiveData<List<String>> get() = _categoryList

    fun getCategoriesList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getCategoriesUseCase.execute()
            _categoryList.postValue(list)
        }

    }


    override fun onCleared() {
        viewModelScope.cancel()
    }
}