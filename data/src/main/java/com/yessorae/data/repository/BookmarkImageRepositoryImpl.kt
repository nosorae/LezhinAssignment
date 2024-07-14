package com.yessorae.data.repository

import androidx.paging.PagingData
import com.yessorae.data.source.local.database.BookmarkImageLocalDBDataSource
import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class BookmarkImageRepositoryImpl @Inject constructor(
    private val lezhinAssignmentLocalDBDataSource: BookmarkImageLocalDBDataSource
) : BookmarkImageRepository {
    override fun getAllImageUrlWithFlow(): Flow<Set<String>> {
        return lezhinAssignmentLocalDBDataSource.getAllBookmarkImageUrl()
    }

    override fun getPagedBookmarkImage(keyword: String): Flow<List<ImageSearchResult>> {
        return lezhinAssignmentLocalDBDataSource.getPagedBookmarkImage(keyword = keyword)
    }

    override suspend fun insertBookmarkImage(imageSearchResult: ImageSearchResult) {
        lezhinAssignmentLocalDBDataSource.insertBookmarkImage(imageSearchResult)
    }

    override suspend fun deleteBookmarkImage(imageUrls: List<String>) {
        lezhinAssignmentLocalDBDataSource.deleteBookmarkImage(imageUrls = imageUrls)
    }
}
