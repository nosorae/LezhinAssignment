package com.yessorae.domain.entity

data class SearchedImage(
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val keyword: String
)
