package com.test.newsline.models

import com.google.gson.annotations.SerializedName


data class Article(

    @SerializedName("uri") var uri: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("source") var source: String? = null,
    @SerializedName("published_date") var publishedDate: String? = null,
    @SerializedName("updated") var updated: String? = null,
    @SerializedName("section") var section: String? = null,
    @SerializedName("subsection") var subsection: String? = null,
    @SerializedName("nytdsection") var nytdsection: String? = null,
    @SerializedName("adx_keywords") var adxKeywords: String? = null,
    @SerializedName("column") var column: String? = null,
    @SerializedName("byline") var byline: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("abstract") var abstract: String? = null,
    @SerializedName("media") var media: ArrayList<Media>? = null,
) : java.io.Serializable