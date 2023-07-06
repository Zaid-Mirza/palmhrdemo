package com.test.newsline.models

import com.google.gson.annotations.SerializedName


data class GeneralResponse(

    @SerializedName("status") var status: String? = null,
    @SerializedName("copyright") var copyright: String? = null,
    @SerializedName("num_results") var numResults: Int? = null,
    @SerializedName("results") var results: ArrayList<Article> = arrayListOf()

)