package com.yessorae.domain.usecase.bookmark

import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetBookmarkImageUseCase @Inject constructor(
    private val bookmarkImageRepository: BookmarkImageRepository
) {
    operator fun invoke(keyword: String): Flow<List<ImageSearchResult>> = bookmarkImageRepository.getPagedBookmarkImage(keyword = keyword)
}
