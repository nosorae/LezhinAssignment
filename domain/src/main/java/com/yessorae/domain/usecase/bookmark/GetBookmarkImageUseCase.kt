package com.yessorae.domain.usecase.bookmark

import androidx.paging.PagingData
import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.BookmarkImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkImageUseCase @Inject constructor(
    private val bookmarkImageRepository: BookmarkImageRepository
) {
    operator fun invoke(keyword: String): Flow<List<ImageSearchResult>> =
        bookmarkImageRepository.getPagedBookmarkImage(keyword = keyword)
}