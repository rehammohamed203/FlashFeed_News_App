package com.reham11203.domain.usecases

import com.reham11203.domain.model.News
import com.reham11203.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend fun invoke(sourceId: String): List<News> {
        val newsList = newsRepository.getNews(sourceId)
        return newsList
    }
}