package com.yessorae.data.di

import com.yessorae.data.repository.BookmarkImageRepositoryImpl
import com.yessorae.data.repository.ImageSearchResultRepositoryImpl
import com.yessorae.domain.respository.BookmarkImageRepository
import com.yessorae.domain.respository.ImageSearchResultRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindImageSearchRepository(imageSearchRepository: ImageSearchResultRepositoryImpl): ImageSearchResultRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkImageRepository(bookmarkImageRepository: BookmarkImageRepositoryImpl): BookmarkImageRepository
}
