package com.yessorae.domain.respository

import com.yessorae.domain.entity.ImageSearchResult
import kotlinx.coroutines.flow.Flow

interface BookmarkImageRepository {
    fun getAllImageUrlWithFlow(): Flow<Set<String>>
    suspend fun insertBookmarkImage(imageSearchResult: ImageSearchResult)
    suspend fun deleteBookmarkImage(imageUrl: String)
}
