package com.yessorae.presentation.screen.bookmark.model

sealed interface BookmarkScreenState {
    data object Loading : BookmarkScreenState

    data class Success(
        val bookmarkImages: List<BookmarkImageUi>
    ) : BookmarkScreenState

    data object EmptyBookmarkImage : BookmarkScreenState

    data object EmptyFilteredBookmarkImage : BookmarkScreenState

    data class Edit(
        val selectableBookmarkImages: List<SelectableBookmarkImageUi>,
        val showDeleteButton: Boolean = false
    ) : BookmarkScreenState

    data object Error : BookmarkScreenState
}
