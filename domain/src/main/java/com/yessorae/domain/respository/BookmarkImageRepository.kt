package com.yessorae.domain.respository

import com.yessorae.domain.entity.SearchedImage
import kotlinx.coroutines.flow.Flow

interface BookmarkImageRepository {
    fun getAllImageUrlWithFlow(): Flow<Set<String>>
    fun getPagedBookmarkImage(keyword: String): Flow<List<SearchedImage>>
    suspend fun insertBookmarkImage(searchedImage: SearchedImage)
    suspend fun deleteBookmarkImage(imageUrls: List<String>)
}
