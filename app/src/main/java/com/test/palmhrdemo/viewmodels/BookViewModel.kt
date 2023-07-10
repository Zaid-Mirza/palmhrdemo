package com.test.palmhrdemo.viewmodels

import androidx.lifecycle.*
import com.test.palmhrdemo.models.GeneralResponse
import com.test.palmhrdemo.models.Items
import com.test.palmhrdemo.models.Resource
import com.test.palmhrdemo.repositories.BooksRepository
import com.test.palmhrdemo.utils.AppEnums
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BooksRepository) : ViewModel() {
    private val booksLiveData = MutableLiveData<Resource<GeneralResponse>?>()
    private val detailLiveData = MutableLiveData<Resource<Items>?>()

    fun getBooks(
        q: String,
        maxResults: String,
        startIndex: String, isRemove: Boolean = false
    ): LiveData<Resource<GeneralResponse>?> {

        if (isRemove) {
            booksLiveData.postValue(null)
            return booksLiveData
        }
        val handler = CoroutineExceptionHandler { data, exception ->
            booksLiveData.postValue(Resource.error("", null, AppEnums.ErrorType.Service))

        }
        viewModelScope.launch(handler) {
            repository.getBooks(
                q,
                maxResults,
                startIndex
            ) {
                booksLiveData.postValue(it)
            }
        }
        return booksLiveData
    }

    fun getBookDetail(
        selfLink: String
    ): LiveData<Resource<Items>?> {


        val handler = CoroutineExceptionHandler { data, exception ->
            detailLiveData.postValue(Resource.error("", null, AppEnums.ErrorType.Service))
        }
        viewModelScope.launch(handler) {
            repository.getBookDetail(
                selfLink
            ) {
                detailLiveData.postValue(it)
            }
        }
        return detailLiveData
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

