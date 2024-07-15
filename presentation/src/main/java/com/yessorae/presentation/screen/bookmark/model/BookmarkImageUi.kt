package com.yessorae.presentation.screen.bookmark.model

import com.yessorae.domain.entity.ImageSearchResult

data class BookmarkImageUi(
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val keyword: String
)

data class SelectableBookmarkImageUi(
    val bookmarkImageUi: BookmarkImageUi,
    val selected: Boolean
)

fun BookmarkImageUi.asSelectableUiModel(selected: Boolean): SelectableBookmarkImageUi =
    SelectableBookmarkImageUi(
        bookmarkImageUi = this,
        selected = selected
    )

fun ImageSearchResult.asUiModel(): BookmarkImageUi =
    BookmarkImageUi(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height,
        keyword = keyword
    )
