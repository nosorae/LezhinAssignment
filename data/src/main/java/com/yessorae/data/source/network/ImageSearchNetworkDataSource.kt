package com.yessorae.data.source.network

import androidx.paging.PagingSource
import com.yessorae.data.source.network.kakao.model.ImageDto

interface ImageSearchNetworkDataSource {
    fun getImageResultPagingSource(keyword: String): PagingSource<Int, ImageDto>
}
