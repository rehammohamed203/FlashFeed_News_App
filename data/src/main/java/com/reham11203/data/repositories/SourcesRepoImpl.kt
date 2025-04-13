package com.reham11203.data.repositories

import com.reham11203.data.dataSources.SourcesOnlineDataSource
import com.reham11203.domain.model.Source
import com.reham11203.domain.repositories.SourcesRepository
import javax.inject.Inject

class SourcesRepoImpl @Inject constructor(private val sourcesOnlineDataSource: SourcesOnlineDataSource) :
    SourcesRepository {

    override suspend fun getSources(categoryId: String): List<Source> {
        val sources = sourcesOnlineDataSource.getSources(categoryId)
        return sources
    }
}