package com.reham11203.data.dataSourcesImpl

import com.reham11203.data.api.WebServices
import com.reham11203.data.dataSources.NewsOnlineDataSource
import com.reham11203.domain.model.News
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    NewsOnlineDataSource {
    override suspend fun getNews(sourceId: String): List<News> {
        val response = webServices.getNews(sourceId)
        val newsList =
            response.newsList?.filterNotNull()?.map { newsDTO -> newsDTO.toNews() }?.toList()
        return newsList ?: listOf()
    }
}