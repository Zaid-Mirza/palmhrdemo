package com.test.palmhrdemo.interfaces


import com.test.palmhrdemo.models.GeneralResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IService {

    @GET("{section}/{days}.json")
    suspend fun getBooks(
        @Path("section") section: String,
        @Path("days") days: String,
        @Query("api-key") apiKey: String
    ): Response<GeneralResponse>


}
