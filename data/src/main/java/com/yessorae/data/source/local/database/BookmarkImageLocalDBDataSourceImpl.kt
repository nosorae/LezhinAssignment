package com.yessorae.data.source.local.database

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yessorae.data.common.Constants
import com.yessorae.data.source.local.database.dao.BookmarkImageDao
import com.yessorae.data.source.local.database.model.asDomainModel
import com.yessorae.data.source.local.database.model.asEntity
import com.yessorae.domain.entity.ImageSearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkImageLocalDBDataSourceImpl @Inject constructor(
    private val bookmarkImageDao: BookmarkImageDao
) : BookmarkImageLocalDBDataSource {
    override fun getAllBookmarkImageUrl(): Flow<Set<String>> {
        return bookmarkImageDao.getAllBookmarkImageUrl().map { it.toSet() }
    }

    override fun getPagedBookmarkImage(keyword: String): Flow<List<ImageSearchResult>> {
        return bookmarkImageDao.getBookmarkImagePagingSource(keyword = keyword).map { list ->
            list.map { entity -> entity.asDomainModel() }
        }
    }

    override suspend fun insertBookmarkImage(bookmarkImage: ImageSearchResult) {
        bookmarkImageDao.insert(bookmarkImage.asEntity())
    }

    override suspend fun deleteBookmarkImage(imageUrls: List<String>) {
        imageUrls.forEach { imageUrl ->
            bookmarkImageDao.deleteByImageUrl(imageUrl)
        }
    }
}
