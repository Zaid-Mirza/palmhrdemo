package com.test.palmhrdemo.repositories

import com.test.palmhrdemo.managers.NetworkManager
import com.test.palmhrdemo.models.GeneralResponse
import com.test.palmhrdemo.models.Resource
import com.test.palmhrdemo.utils.AppEnums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository {


    suspend fun getBooks(
        q: String,
        maxResults: String,
        startIndex: String,
        listener: suspend (Resource<GeneralResponse>) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            val response = NetworkManager.getBooks(q, maxResults,startIndex)
            if (response?.isSuccessful == true) {
                response.body()?.let {
                    listener(Resource.success(it))
                }
            } else {
                listener(Resource.error(response?.message(), null, AppEnums.ErrorType.Service))
            }
        }

    }

}