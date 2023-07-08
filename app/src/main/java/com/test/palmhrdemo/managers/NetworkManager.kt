package com.test.palmhrdemo.managers


import com.test.palmhrdemo.BuildConfig
import com.test.palmhrdemo.models.GeneralResponse
import com.test.palmhrdemo.models.Items
import com.test.palmhrdemo.networks.RetrofitHelper
import retrofit2.Response

object NetworkManager {

    suspend fun getBooks(
        q: String, maxResults: String, startIndex: String
    ): Response<GeneralResponse>? {

        return RetrofitHelper.client?.getBooks(q, "20", startIndex, BuildConfig.API_KEY)
    }

    suspend fun getBookDetail(selfLink: String): Response<Items>? {

        return RetrofitHelper.client?.getBookDetail(selfLink)
    }
}