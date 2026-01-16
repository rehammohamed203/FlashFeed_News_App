package com.reham11203.data.dataSources

import com.reham11203.data.dataSourcesImpl.NewsOnlineDataSourceImpl
import com.reham11203.data.dataSourcesImpl.SourcesOnlineDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class OnlineDataSourceBinder() {

    @Binds
    abstract fun bindNewsOnlineDataSource(
        newsOnlineDataSourceImpl: NewsOnlineDataSourceImpl
    ): NewsOnlineDataSource

    @Binds
    abstract fun bindSourcesOnlineDataSource(
        sourcesOnlineDataSourceImpl: SourcesOnlineDataSourceImpl
    ): SourcesOnlineDataSource
}