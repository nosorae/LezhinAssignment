package com.yessorae.data.repository

import com.yessorae.data.source.local.database.BookmarkImageLocalDBDataSource
import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.BookmarkImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarkImageRepositoryImpl @Inject constructor(
    private val lezhinAssignmentLocalDBDataSource: BookmarkImageLocalDBDataSource
) : BookmarkImageRepository{
    override fun getAllImageUrlWithFlow(): Flow<Set<String>> {
        return lezhinAssignmentLocalDBDataSource.getAllBookmarkImage()
    }

    override suspend fun insertBookmarkImage(imageSearchResult: ImageSearchResult) {
        lezhinAssignmentLocalDBDataSource.insertBookmarkImage(imageSearchResult)
    }

    override suspend fun deleteBookmarkImage(imageUrl: String) {
        lezhinAssignmentLocalDBDataSource.deleteBookmarkImage(imageUrl = imageUrl)
    }
}
