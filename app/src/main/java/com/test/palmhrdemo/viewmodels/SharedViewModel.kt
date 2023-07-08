package com.test.palmhrdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.palmhrdemo.models.FilterObject


class SharedViewModel() : ViewModel() {


    private val selectedData = MutableLiveData<FilterObject?>()

    fun setSelectedFilterData(filterObject: FilterObject?) {
        selectedData.postValue(filterObject)
    }

    fun getSelectedFilterData(): LiveData<FilterObject?> {
        return selectedData
    }


}