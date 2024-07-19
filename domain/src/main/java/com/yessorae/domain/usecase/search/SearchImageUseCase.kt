package com.yessorae.domain.usecase.search

import androidx.paging.PagingData
import com.yessorae.domain.entity.SearchedImage
import com.yessorae.domain.respository.ImageSearchResultRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchImageUseCase @Inject constructor(
    private val imageSearchResultRepository: ImageSearchResultRepository
) {
    operator fun invoke(keyword: String): Flow<PagingData<SearchedImage>> {
        return imageSearchResultRepository.getPagedImageSearchResult(keyword = keyword)
    }
}
