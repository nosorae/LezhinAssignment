package com.yessorae.presentation.ui.bookmark

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val BOOKMARK_ROUTE = "bookmark_route"

fun NavController.navigateToBookmark(navOptions: NavOptions? = null) {
    this.navigate(BOOKMARK_ROUTE, navOptions)
}

fun NavGraphBuilder.bookmarkScreen() {
    composable(
        route = BOOKMARK_ROUTE
    ) {
        BookmarkScreenRoute()
    }
}
