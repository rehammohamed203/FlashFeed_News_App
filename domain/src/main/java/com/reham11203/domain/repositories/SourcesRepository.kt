package com.reham11203.domain.repositories

import com.reham11203.domain.model.Source

interface SourcesRepository {
    suspend fun getSources(categoryId: String): List<Source>
}