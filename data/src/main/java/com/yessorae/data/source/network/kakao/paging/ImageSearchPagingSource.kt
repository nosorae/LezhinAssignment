package com.yessorae.data.source.network.kakao.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yessorae.data.source.network.kakao.api.KakaoImageSearchApi
import com.yessorae.data.source.network.kakao.common.KakaoApiConstants
import com.yessorae.data.source.network.kakao.model.ImageDto
import java.io.IOException
import retrofit2.HttpException

class ImageSearchPagingSource(
    private val kakaoImageSearchApi: KakaoImageSearchApi,
    private val keyword: String
) : PagingSource<Int, ImageDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageDto> {
        assert(params.loadSize <= KakaoApiConstants.MAX_PAGE_SIZE) {
            "Page size must be less than or equal to ${KakaoApiConstants.MAX_PAGE_SIZE}"
        }

        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = kakaoImageSearchApi.getImageSearchResult(
                keyword = keyword,
                pageNumber = page,
                pageSize = params.loadSize
            )
            val imageDto = response.imageDto
            val isLastPage = imageDto.size < params.loadSize || response.metaDto.isEnd == true || page > KakaoApiConstants.MAX_PAGE_NUMBER

            LoadResult.Page(
                data = imageDto,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (isLastPage) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
