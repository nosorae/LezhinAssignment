package com.yessorae.data.source.network.kakao.model

import com.yessorae.domain.entity.ImageSearchResult
import kotlinx.serialization.SerialName
import  kotlinx.serialization.Serializable

@Serializable
data class ImageSearchResponseDto(
    @SerialName("documents")
    val imageDto: List<ImageDto>,
    @SerialName("meta")
    val metaDto: MetaDto
)

@Serializable
data class ImageDto(
    @SerialName("thumbnail_url")
    val thumbnailUrl: String?,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("height")
    val height: Int?,
    @SerialName("width")
    val width: Int?
//    @SerialName("collection")
//    val collection: String? = null,
//    @SerialName("datetime")
//    val datetime: String? = null,
//    @SerialName("display_sitename")
//    val displaySiteName: String? = null,
//    @SerialName("doc_url")
//    val docUrl: String? = null,
) {
    fun asDomainModel() = ImageSearchResult(
        thumbnailUrl = thumbnailUrl ?: "",
        imageUrl = imageUrl ?: "",
        width = width ?: 0,
        height = height ?: 0
    )
}

@Serializable
data class MetaDto(
    @SerialName("is_end")
    val isEnd: Boolean?,
    @SerialName("pageable_count")
    val pageableCount: Int?,
    @SerialName("total_count")
    val totalCount: Int?
)

