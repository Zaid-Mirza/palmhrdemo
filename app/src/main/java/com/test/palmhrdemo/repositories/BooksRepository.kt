package com.test.palmhrdemo.repositories

import com.test.newsline.managers.NetworkManager
import com.test.newsline.models.GeneralResponse
import com.test.newsline.models.Resource
import com.test.palmhrdemo.utils.AppEnums
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository {


    suspend fun getBooks(
        section: String,
        days: String,
        listener: suspend (Resource<GeneralResponse>) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            val response = NetworkManager.getArticles(section, days)
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