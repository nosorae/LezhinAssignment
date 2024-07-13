package com.yessorae.presentation.ui.saerch

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SEARCH_NAVIGATION = "search_navigation"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(SEARCH_NAVIGATION, navOptions)
}

fun NavGraphBuilder.searchScreen() {
    composable(
        route = SEARCH_NAVIGATION
    ) {
        SearchRoute()
    }
}
