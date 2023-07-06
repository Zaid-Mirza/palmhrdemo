package com.test.palmhrdemo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.palmhrdemo.models.FilterObject


class SharedViewModel() : ViewModel() {


    private val _filterObject = MutableLiveData<FilterObject>()

    var filterObject = _filterObject


}