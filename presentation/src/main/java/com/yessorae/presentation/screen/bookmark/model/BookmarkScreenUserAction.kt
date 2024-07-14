package com.yessorae.presentation.screen.bookmark.model

sealed interface BookmarkScreenUserAction {
    data class ChangeSearchKeyword(val keyword: String) : BookmarkScreenUserAction

    data object ClearSearchKeyword : BookmarkScreenUserAction

    data class ClickDeleteSingleBookmark(val bookmark: BookmarkImageUi) : BookmarkScreenUserAction

    data class ClickEditMode(
        val screenState: BookmarkScreenState.Success
    ) : BookmarkScreenUserAction

    data class ClickBookmarkImageForMultipleDelete(val selectableBookmarkImageUi: SelectableBookmarkImageUi) : BookmarkScreenUserAction

    data class ClickCancelEditMode(
        val screenState: BookmarkScreenState.Edit
    ) : BookmarkScreenUserAction

    data class ClickDeleteMultipleBookmark(val bookmarks: List<BookmarkImageUi>) : BookmarkScreenUserAction
}
