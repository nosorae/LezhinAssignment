package com.yessorae.data.di

import com.yessorae.data.source.local.database.BookmarkImageLocalDBDataSource
import com.yessorae.data.source.local.database.BookmarkImageLocalDBDataSourceImpl
import com.yessorae.data.source.network.ImageSearchNetworkDataSource
import com.yessorae.data.source.network.kakao.ImageSearchNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindImageSearchNetworkDataSource(networkDataSource: ImageSearchNetworkDataSourceImpl): ImageSearchNetworkDataSource

    @Binds
    @Singleton
    abstract fun bindBookmarkImageLocalDBDataSource(localDBDataSource: BookmarkImageLocalDBDataSourceImpl): BookmarkImageLocalDBDataSource
}
