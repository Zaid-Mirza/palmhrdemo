package com.test.palmhrdemo.networks

import com.google.gson.Gson
import com.test.palmhrdemo.BuildConfig
import com.test.palmhrdemo.interfaces.IService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

/**
 * network api to handle all rest services
 */
class RetrofitHelper<T> {


    companion object {

        var BASE_URL = BuildConfig.BASE_URL

        private var restInterface: IService? = null

        private var okHttpClient: OkHttpClient.Builder? = null

        val client: IService?
            get() = restInterface ?: getRestClient()
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GeneralParser.getGSONConfiguration()))
                .build()
        }

        private fun getRestClient(): IService? {

            restInterface = retrofit.create(IService::class.java)
            return restInterface
        }

        /**
         * build retrofit request
         *
         * @param gson Gson data
         * @return Retrofit
         */
        private fun buildRetrofit(gson: Gson): Retrofit {

            return Retrofit.Builder()
                .client(getNewHTTPClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }


        fun getHTTPClient(): OkHttpClient {
            return if (okHttpClient == null) {
                getNewHTTPClient()
            } else
                okHttpClient?.build()!!
        }

        private fun getNewHTTPClient(): OkHttpClient {
            val cookieManager = CookieManager()
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

            okHttpClient = OkHttpClient.Builder()
            okHttpClient?.connectTimeout(30, TimeUnit.SECONDS)
            okHttpClient?.readTimeout(30, TimeUnit.SECONDS)
            okHttpClient?.writeTimeout(30, TimeUnit.SECONDS)
            // okHttpClient?.callTimeout(2, TimeUnit.SECONDS)
            return okHttpClient?.build()!!
        }
    }
}