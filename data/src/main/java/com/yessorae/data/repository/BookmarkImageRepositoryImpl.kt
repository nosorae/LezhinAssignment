package com.yessorae.data.repository

import com.yessorae.data.di.Dispatcher
import com.yessorae.data.di.LezhinAssignmentDispatcher
import com.yessorae.data.source.local.database.BookmarkImageLocalDBDataSource
import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class BookmarkImageRepositoryImpl @Inject constructor(
    private val lezhinAssignmentLocalDBDataSource: BookmarkImageLocalDBDataSource,
    @Dispatcher(LezhinAssignmentDispatcher.IO)
    private val dispatcher: CoroutineDispatcher
) : BookmarkImageRepository {
    override fun getAllImageUrlWithFlow(): Flow<Set<String>> {
        return lezhinAssignmentLocalDBDataSource.getAllBookmarkImageUrl().flowOn(dispatcher)
    }

    override fun getPagedBookmarkImage(keyword: String): Flow<List<ImageSearchResult>> {
        return lezhinAssignmentLocalDBDataSource.getPagedBookmarkImage(keyword = keyword).flowOn(dispatcher)
    }

    override suspend fun insertBookmarkImage(imageSearchResult: ImageSearchResult) =
        withContext(dispatcher) {
            lezhinAssignmentLocalDBDataSource.insertBookmarkImage(imageSearchResult)
        }

    override suspend fun deleteBookmarkImage(imageUrls: List<String>) =
        withContext(dispatcher) {
            lezhinAssignmentLocalDBDataSource.deleteBookmarkImage(imageUrls = imageUrls)
        }
}
