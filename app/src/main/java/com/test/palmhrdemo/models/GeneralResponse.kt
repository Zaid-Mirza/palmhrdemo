package com.test.palmhrdemo.models

import com.google.gson.annotations.SerializedName


data class GeneralResponse(

    @SerializedName("kind") var kind: String? = null,
    @SerializedName("totalItems") var totalItems: Int? = null,
    @SerializedName("items") var items: ArrayList<Items> = arrayListOf()

)