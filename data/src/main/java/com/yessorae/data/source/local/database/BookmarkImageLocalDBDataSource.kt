package com.yessorae.data.source.local.database

import com.yessorae.domain.entity.ImageSearchResult
import kotlinx.coroutines.flow.Flow

interface BookmarkImageLocalDBDataSource {
    fun getAllBookmarkImage(): Flow<Set<String>>
    suspend fun insertBookmarkImage(bookmarkImage: ImageSearchResult)
    suspend fun deleteBookmarkImage(imageUrl: String)
}
