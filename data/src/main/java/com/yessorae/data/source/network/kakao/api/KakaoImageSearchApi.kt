package com.yessorae.data.source.network.kakao.api

import com.yessorae.data.source.network.kakao.common.KakaoApiConstants
import com.yessorae.data.source.network.kakao.model.ImageSearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoImageSearchApi {
    @GET(KakaoApiConstants.GET_IMAGE_SEARCH_RESULT_PATH)
    suspend fun getImageSearchResult(
        @Query("query") keyword: String,
        @Query("page") pageNumber: Int,
        @Query("size") pageSize: Int
    ): ImageSearchResponseDto
}
