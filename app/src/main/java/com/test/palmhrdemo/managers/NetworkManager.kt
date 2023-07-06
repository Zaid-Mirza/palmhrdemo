package com.test.palmhrdemo.managers


import com.test.palmhrdemo.BuildConfig
import com.test.palmhrdemo.models.GeneralResponse
import com.test.palmhrdemo.networks.RetrofitHelper
import retrofit2.Response
import retrofit2.http.Query

object NetworkManager {

    suspend fun getBooks(q: String, maxResults: String, startIndex: String
    ): Response<GeneralResponse>? {

        return RetrofitHelper.client?.getBooks(q, "20",startIndex, BuildConfig.API_KEY)
    }
}