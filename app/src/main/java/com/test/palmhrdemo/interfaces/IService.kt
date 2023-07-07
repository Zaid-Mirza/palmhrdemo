package com.test.palmhrdemo.interfaces


import com.test.palmhrdemo.models.GeneralResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IService {
    @GET("v1/volumes")
    suspend fun getBooks(
        @Query("q") q: String,
        @Query("maxResults") maxResults: String,
        @Query("startIndex") startIndex: String,
        @Query("key") key: String
    ): Response<GeneralResponse>


}
