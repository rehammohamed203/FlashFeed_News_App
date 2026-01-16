package com.reham11203.data.repositories

import com.reham11203.data.dataSources.NewsOnlineDataSource
import com.reham11203.domain.model.News
import com.reham11203.domain.repositories.NewsRepository
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(private val newsOnlineDataSource: NewsOnlineDataSource) :
    NewsRepository {

    override suspend fun getNews(sourceId: String): List<News> {
        val newsList = newsOnlineDataSource.getNews(sourceId)
        return newsList
    }
}