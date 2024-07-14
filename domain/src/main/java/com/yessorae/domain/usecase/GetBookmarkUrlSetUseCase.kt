package com.yessorae.domain.usecase

import com.yessorae.domain.respository.BookmarkImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkUrlSetUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkImageRepository
) {
    operator fun invoke(): Flow<Set<String>> = bookmarkRepository.getAllImageUrlWithFlow()
}
