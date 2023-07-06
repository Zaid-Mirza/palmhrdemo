package com.test.palmhrdemo.viewmodels

import androidx.lifecycle.*
import com.test.palmhrdemo.models.GeneralResponse
import com.test.palmhrdemo.models.Resource
import com.test.palmhrdemo.repositories.BooksRepository
import com.test.palmhrdemo.utils.AppEnums
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BooksRepository) : ViewModel() {
    private val articlesLiveData = MutableLiveData<Resource<GeneralResponse>?>()

    fun getBooks(section: String, days: String): LiveData<Resource<GeneralResponse>?> {


        val handler = CoroutineExceptionHandler { data, exception ->
            val ss = ""
            articlesLiveData.postValue(Resource.error("", null, AppEnums.ErrorType.Service))

        }
        viewModelScope.launch(handler) {
            repository.getBooks(section, days) {
                articlesLiveData.postValue(it)
            }
        }
        return articlesLiveData
    }
}


class BooksViewModelFactory(private val repository: BooksRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

