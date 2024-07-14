package com.yessorae.presentation.screen.saerch.model

sealed interface SearchScreenUserAction {
    data class SearchKeywordChange(val keyword: String) : SearchScreenUserAction
    data object SearchKeywordClear : SearchScreenUserAction
}
