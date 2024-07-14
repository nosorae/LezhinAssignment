package com.yessorae.domain.respository

import androidx.paging.PagingData
import com.yessorae.domain.entity.ImageSearchResult
import kotlinx.coroutines.flow.Flow

interface ImageSearchResultRepository {
    fun getPagedImageSearchResult(keyword: String): Flow<PagingData<ImageSearchResult>>
}

