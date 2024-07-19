package com.yessorae.presentation.screen.saerch.model

sealed interface SearchScreenUserAction {
    data class ChangeSearchKeyword(val keyword: String) : SearchScreenUserAction
    data object ClearSearchKeyword : SearchScreenUserAction
    data class ClickBookmark(val imageUi: ImageUi) : SearchScreenUserAction
}
