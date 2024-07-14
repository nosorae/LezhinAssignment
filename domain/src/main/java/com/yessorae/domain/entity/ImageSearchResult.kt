package com.yessorae.domain.entity

data class ImageSearchResult(
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val keyword: String
)
