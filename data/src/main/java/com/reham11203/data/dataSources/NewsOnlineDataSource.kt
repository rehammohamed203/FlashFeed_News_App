package com.reham11203.data.dataSources

import com.reham11203.domain.model.News

interface NewsOnlineDataSource {
    suspend fun getNews(sourceId: String): List<News>

}