package com.yessorae.presentation.screen.saerch.model

import com.yessorae.domain.entity.ImageSearchResult

data class ImageUi(
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val isBookmark: Boolean
)

fun ImageSearchResult.asUiModel(isBookmark: Boolean): ImageUi =
    ImageUi(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height,
        isBookmark = isBookmark
    )

fun ImageUi.asDomainModel(): ImageSearchResult =
    ImageSearchResult(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height
    )
