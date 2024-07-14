package com.yessorae.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yessorae.data.common.Constants
import com.yessorae.data.source.network.ImageSearchNetworkDataSource
import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.ImageSearchResultRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageSearchResultRepositoryImpl @Inject constructor(
    private val imageSearchNetworkDataSource: ImageSearchNetworkDataSource
) : ImageSearchResultRepository {
    override fun getPagedImageSearchResult(keyword: String): Flow<PagingData<ImageSearchResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGE_SIZE,
                initialLoadSize = Constants.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { imageSearchNetworkDataSource.getImageResultPagingSource(keyword = keyword) }
        )
            .flow
            .map { pagingData ->
                pagingData.map { imageDto ->
                    imageDto.asDomainModel()
                }
            }
    }

}