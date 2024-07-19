package com.yessorae.presentation.screen.bookmark

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val BOOKMARK_ROUTE = "bookmark_route"

fun NavGraphBuilder.bookmarkScreen() {
    composable(
        route = BOOKMARK_ROUTE
    ) {
        BookmarkScreenRoute()
    }
}
