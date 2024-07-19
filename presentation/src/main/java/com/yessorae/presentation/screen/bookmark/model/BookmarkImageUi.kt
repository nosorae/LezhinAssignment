package com.yessorae.presentation.screen.bookmark.model

import androidx.compose.runtime.Immutable
import com.yessorae.domain.entity.SearchedImage

@Immutable
data class BookmarkImageUi(
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val keyword: String
)

@Immutable
data class SelectableBookmarkImageUi(
    val bookmarkImageUi: BookmarkImageUi,
    val selected: Boolean
)

fun BookmarkImageUi.asSelectableUiModel(selected: Boolean): SelectableBookmarkImageUi =
    SelectableBookmarkImageUi(
        bookmarkImageUi = this,
        selected = selected
    )

fun SearchedImage.asUiModel(): BookmarkImageUi =
    BookmarkImageUi(
        thumbnailUrl = thumbnailUrl,
        imageUrl = imageUrl,
        width = width,
        height = height,
        keyword = keyword
    )
