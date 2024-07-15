package com.yessorae.domain.usecase.search

import com.yessorae.domain.respository.BookmarkImageRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetBookmarkUrlSetUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkImageRepository
) {
    operator fun invoke(): Flow<Set<String>> = bookmarkRepository.getAllImageUrlWithFlow()
}
