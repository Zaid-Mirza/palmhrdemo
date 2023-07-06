package com.test.palmhrdemo.managers


import com.test.palmhrdemo.BuildConfig
import com.test.palmhrdemo.models.GeneralResponse
import com.test.palmhrdemo.networks.RetrofitHelper
import retrofit2.Response

object NetworkManager {

    suspend  fun getBooks( section:String, days:String
    ) : Response<GeneralResponse>?{

      return RetrofitHelper.client?.getBooks(section,days, BuildConfig.API_KEY)
    }
}