package com.yessorae.data.source.local.database

import com.yessorae.domain.entity.SearchedImage
import kotlinx.coroutines.flow.Flow

interface BookmarkImageLocalDBDataSource {
    fun getAllBookmarkImageUrl(): Flow<Set<String>>
    fun getPagedBookmarkImage(keyword: String): Flow<List<SearchedImage>>
    suspend fun insertBookmarkImage(bookmarkImage: SearchedImage)
    suspend fun deleteBookmarkImage(imageUrls: List<String>)
}
