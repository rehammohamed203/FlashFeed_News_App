package com.reham11203.domain.repositories

import com.reham11203.domain.model.News

interface NewsRepository {
    suspend fun getNews(sourceId: String): List<News>
}