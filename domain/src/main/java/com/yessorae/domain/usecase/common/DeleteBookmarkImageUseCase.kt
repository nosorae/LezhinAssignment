package com.yessorae.domain.usecase.common

import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject

class DeleteBookmarkImageUseCase @Inject constructor(
    private val bookmarkImageRepository: BookmarkImageRepository
) {
    suspend operator fun invoke(imageUrls: List<String>) =
        bookmarkImageRepository.deleteBookmarkImage(imageUrls = imageUrls)
}
