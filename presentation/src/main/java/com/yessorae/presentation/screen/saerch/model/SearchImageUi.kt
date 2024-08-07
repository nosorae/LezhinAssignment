package com.yessorae.presentation.screen.saerch.model

import androidx.compose.runtime.Immutable
import com.yessorae.domain.entity.SearchedImage

@Immutable
data class ImageUi(
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val isBookmark: Boolean,
    val clickData: ClickData
)

@Immutable
data class ClickData(
    val keyword: String
)

fun SearchedImage.asUiModel(isBookmark: Boolean): ImageUi =
    ImageUi(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height,
        isBookmark = isBookmark,
        clickData = ClickData(keyword = keyword)
    )

fun ImageUi.asDomainModel(): SearchedImage =
    SearchedImage(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height,
        keyword = clickData.keyword
    )
