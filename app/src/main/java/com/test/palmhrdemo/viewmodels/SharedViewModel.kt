package com.test.palmhrdemo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.newsline.models.FilterObject

class SharedViewModel() : ViewModel() {


    private val _filterObject = MutableLiveData<FilterObject>()

    var filterObject = _filterObject


}