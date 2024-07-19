package com.yessorae.data.source.network.kakao

import androidx.paging.PagingSource
import com.yessorae.data.source.network.ImageSearchNetworkDataSource
import com.yessorae.data.source.network.kakao.api.KakaoImageSearchApi
import com.yessorae.data.source.network.kakao.model.ImageDto
import com.yessorae.data.source.network.kakao.paging.ImageSearchPagingSource
import javax.inject.Inject

class ImageSearchNetworkDataSourceImpl @Inject constructor(
    private val api: KakaoImageSearchApi
) : ImageSearchNetworkDataSource {
    override fun getImageResultPagingSource(keyword: String): PagingSource<Int, ImageDto> {
        return ImageSearchPagingSource(
            kakaoImageSearchApi = api,
            keyword = keyword
        )
    }
}
