package com.yessorae.presentation.screen.bookmark.model

sealed interface BookmarkScreenState {
    data object Loading : BookmarkScreenState

    data class Success(
        val bookmarkImages: List<BookmarkImageUi>
    ) : BookmarkScreenState

    data object Empty : BookmarkScreenState

    data class Edit(
        val selectableBookmarkImages: List<SelectableBookmarkImageUi>
    ) : BookmarkScreenState

    data object Error : BookmarkScreenState
}