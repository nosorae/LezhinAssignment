package com.yessorae.presentation.screen.bookmark.model

sealed interface BookmarkScreenUserAction {
    data class ChangeSearchKeyword(val keyword: String) : BookmarkScreenUserAction

    data object ClearSearchKeyword : BookmarkScreenUserAction

    data class ClickDeleteSingleBookmark(val bookmark: BookmarkImageUi) : BookmarkScreenUserAction

    data object ClickEditMode : BookmarkScreenUserAction

    data class ClickBookmarkImageForMultipleDelete(val selectableBookmarkImageUi: SelectableBookmarkImageUi) : BookmarkScreenUserAction

    data object ClickCancelEditMode : BookmarkScreenUserAction

    data object ClickDeleteMultipleBookmark : BookmarkScreenUserAction
}
