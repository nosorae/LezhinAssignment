package com.yessorae.domain.usecase

import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject

class ToggleBookmarkImageUseCase @Inject constructor(
    private val bookmarkImageRepository: BookmarkImageRepository
) {
    suspend operator fun invoke(
        params: List<Params>
    ) {
        params.forEach { param ->
            val imageUrl = param.imageUrl
            val old = bookmarkImageRepository.getImage(imageUrl = imageUrl)
            bookmarkImageRepository.updateImageBookmark(
                imageUrl = imageUrl,
                isBookmark = old.isBookmark.not()
            )
        }
    }

    data class Params(
        val imageUrl: String,
        val isBookmarked: Boolean
    )
}