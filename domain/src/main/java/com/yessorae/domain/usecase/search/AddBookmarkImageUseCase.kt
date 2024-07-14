package com.yessorae.domain.usecase.search

import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject

class AddBookmarkImageUseCase @Inject constructor(
    private val bookmarkImageRepository: BookmarkImageRepository
) {
    suspend operator fun invoke(imageSearchResult: ImageSearchResult) =
        bookmarkImageRepository.insertBookmarkImage(imageSearchResult = imageSearchResult)
}
