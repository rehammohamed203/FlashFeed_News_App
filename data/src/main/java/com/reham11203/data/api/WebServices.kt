package com.reham11203.data.api

import com.reham11203.data.models.news.NewsResponse
import com.reham11203.data.models.sources.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("v2/top-headlines/sources")
    suspend fun getSources(@Query("category") categoryId: String): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(@Query("sources") source: String): NewsResponse
}