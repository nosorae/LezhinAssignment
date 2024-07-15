package com.yessorae.domain.respository

import com.yessorae.domain.entity.ImageSearchResult
import kotlinx.coroutines.flow.Flow

interface BookmarkImageRepository {
    fun getAllImageUrlWithFlow(): Flow<Set<String>>
    fun getPagedBookmarkImage(keyword: String): Flow<List<ImageSearchResult>>
    suspend fun insertBookmarkImage(imageSearchResult: ImageSearchResult)
    suspend fun deleteBookmarkImage(imageUrls: List<String>)
}
