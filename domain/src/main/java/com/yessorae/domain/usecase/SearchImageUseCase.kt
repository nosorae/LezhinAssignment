package com.yessorae.domain.usecase

import androidx.paging.PagingData
import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.ImageSearchResultRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchImageUseCase @Inject constructor(
    private val imageSearchResultRepository: ImageSearchResultRepository
) {
    operator fun invoke(keyword: String): Flow<PagingData<ImageSearchResult>> {
        return imageSearchResultRepository.getPagedImageSearchResult(keyword = keyword)
    }
}
