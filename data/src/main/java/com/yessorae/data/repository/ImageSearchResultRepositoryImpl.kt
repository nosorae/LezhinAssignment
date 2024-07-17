package com.yessorae.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.yessorae.data.common.Constants
import com.yessorae.data.di.Dispatcher
import com.yessorae.data.di.LezhinAssignmentDispatcher
import com.yessorae.data.source.network.ImageSearchNetworkDataSource
import com.yessorae.domain.entity.ImageSearchResult
import com.yessorae.domain.respository.ImageSearchResultRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ImageSearchResultRepositoryImpl @Inject constructor(
    private val imageSearchNetworkDataSource: ImageSearchNetworkDataSource,
    @Dispatcher(LezhinAssignmentDispatcher.IO)
    private val dispatcher: CoroutineDispatcher
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
            .flowOn(dispatcher)
            .map { pagingData ->
                pagingData.map { imageDto ->
                    imageDto.asDomainModel(keyword = keyword)
                }
            }
    }
}
