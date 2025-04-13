package com.reham11203.data.dataSources

import com.reham11203.domain.model.Source

interface SourcesOnlineDataSource {

    suspend fun getSources(categoryId: String): List<Source>
}