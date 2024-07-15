package com.yessorae.data.source.local.database

import com.yessorae.domain.entity.ImageSearchResult
import kotlinx.coroutines.flow.Flow

interface BookmarkImageLocalDBDataSource {
    fun getAllBookmarkImageUrl(): Flow<Set<String>>
    fun getPagedBookmarkImage(keyword: String): Flow<List<ImageSearchResult>>
    suspend fun insertBookmarkImage(bookmarkImage: ImageSearchResult)
    suspend fun deleteBookmarkImage(imageUrls: List<String>)
}
