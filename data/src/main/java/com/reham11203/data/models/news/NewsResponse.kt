package com.reham11203.data.models.news
import com.google.gson.annotations.SerializedName


data class NewsResponse(

    @field:SerializedName("totalResults")
	val totalResults: Int? = null,

    @field:SerializedName("articles")
    val newsList: List<NewsDTO?>? = null,

    @field:SerializedName("status")
	val status: String? = null
)