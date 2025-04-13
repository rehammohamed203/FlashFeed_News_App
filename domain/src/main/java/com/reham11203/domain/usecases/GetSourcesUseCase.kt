package com.reham11203.domain.usecases

import com.reham11203.domain.model.Source
import com.reham11203.domain.repositories.SourcesRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(private val sourcesRepository: SourcesRepository) {

    suspend fun getSources(categoryId: String): List<Source> {
        val sources = sourcesRepository.getSources(categoryId)
        return sources
    }
}