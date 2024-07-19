package com.yessorae.presentation.screen.saerch

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SEARCH_ROUTE = "search_navigation"

fun NavGraphBuilder.searchScreen() {
    composable(
        route = SEARCH_ROUTE
    ) {
        SearchRoute()
    }
}
