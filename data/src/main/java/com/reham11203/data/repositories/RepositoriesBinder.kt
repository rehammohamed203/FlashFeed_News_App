package com.reham11203.data.repositories

import com.reham11203.domain.repositories.NewsRepository
import com.reham11203.domain.repositories.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesBinder {

    @Binds
    @ViewModelScoped
    abstract fun bindNewsRepo(newsRepoImpl: NewsRepoImpl): NewsRepository

    @Binds
    @ViewModelScoped
    abstract fun bindSourcesRepo(sourcesRepoImpl: SourcesRepoImpl): SourcesRepository

}