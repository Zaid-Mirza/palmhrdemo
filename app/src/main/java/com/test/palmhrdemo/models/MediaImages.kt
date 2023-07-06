package com.test.newsline.models

import com.google.gson.annotations.SerializedName


data class MediaImages (

  @SerializedName("url"    ) var url    : String? = null,
  @SerializedName("format" ) var format : String? = null,

)