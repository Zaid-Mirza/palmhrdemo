package com.test.palmhrdemo.interfaces


import com.test.palmhrdemo.models.GeneralResponse
import com.test.palmhrdemo.models.Items
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IService {
    @GET("v1/volumes")
    suspend fun getBooks(
        @Query("q") q: String,
        @Query("maxResults") maxResults: String,
        @Query("startIndex") startIndex: String,
        @Query("key") key: String
    ): Response<GeneralResponse>

    @GET
    suspend fun getBookDetail(
        @Url selfLink: String,
    ): Response<Items>


}
