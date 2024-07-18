package com.yessorae.domain.usecase.search

import com.yessorae.domain.entity.SearchedImage
import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject

class AddBookmarkImageUseCase @Inject constructor(
    private val bookmarkImageRepository: BookmarkImageRepository
) {
    suspend operator fun invoke(searchedImage: SearchedImage) =
        bookmarkImageRepository.insertBookmarkImage(searchedImage = searchedImage)
}
