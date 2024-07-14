package com.yessorae.presentation.screen.saerch.model

import com.yessorae.domain.entity.ImageSearchResult

data class ImageUi( // TODO::SR-N 이름 변경
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int
)

fun ImageSearchResult.asImageUi() =
    ImageUi(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height
    )
