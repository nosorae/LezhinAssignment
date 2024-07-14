package com.yessorae.data.source.local.database

import com.yessorae.data.source.local.database.dao.BookmarkImageDao
import com.yessorae.data.source.local.database.model.asEntity
import com.yessorae.domain.entity.ImageSearchResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookmarkImageLocalDBDataSourceImpl @Inject constructor(
    private val bookmarkImageDao: BookmarkImageDao
) : BookmarkImageLocalDBDataSource {
    override fun getAllBookmarkImage(): Flow<Set<String>> {
        return bookmarkImageDao.getAllBookmarkImageUrl().map { it.toSet() }
    }

    override suspend fun insertBookmarkImage(bookmarkImage: ImageSearchResult) {
        bookmarkImageDao.insert(bookmarkImage.asEntity())
    }

    override suspend fun deleteBookmarkImage(imageUrl: String) {
        bookmarkImageDao.deleteByImageUrl(imageUrl)
    }
}
