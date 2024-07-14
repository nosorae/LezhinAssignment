package com.yessorae.domain.usecase

import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject

class DeleteBookmarkImageUseCase @Inject constructor(
    private val bookmarkImageRepository: BookmarkImageRepository
) {
    suspend operator fun invoke(imageUrl: String) = bookmarkImageRepository.deleteBookmarkImage(imageUrl = imageUrl)
}
