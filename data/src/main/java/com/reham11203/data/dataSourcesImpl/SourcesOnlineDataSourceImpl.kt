package com.reham11203.data.dataSourcesImpl

import com.reham11203.data.api.WebServices
import com.reham11203.data.dataSources.SourcesOnlineDataSource
import com.reham11203.domain.model.Source
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(private val webServices: WebServices) :
    SourcesOnlineDataSource {
    override suspend fun getSources(categoryId: String): List<Source> {
        val response = webServices.getSources(categoryId)
        val sources =
            response.sources?.filterNotNull()?.map { sourceDTO -> sourceDTO.toSource() }?.toList()
        return sources ?: listOf()
    }
}