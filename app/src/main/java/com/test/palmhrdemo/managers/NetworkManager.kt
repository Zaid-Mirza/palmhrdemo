package com.test.newsline.managers


import com.test.newsline.BuildConfig
import com.test.newsline.models.GeneralResponse
import com.test.newsline.networks.GeneralParser
import com.test.newsline.networks.RetrofitHelper
import retrofit2.Response

object NetworkManager {

    suspend  fun getArticles( section:String, days:String
    ) : Response<GeneralResponse>?{

      return RetrofitHelper.client?.getArticles(section,days,BuildConfig.API_KEY)
    }
}