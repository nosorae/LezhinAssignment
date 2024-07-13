package com.yessorae.presentation.screen.main.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.yessorae.presentation.screen.bookmark.bookmarkScreen
import com.yessorae.presentation.screen.saerch.SEARCH_ROUTE
import com.yessorae.presentation.screen.saerch.searchScreen

@Composable
fun MainScreenNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = SEARCH_ROUTE
) {
    NavHost(
        navController = navController,
        modifier = modifier.fillMaxSize(),
        startDestination = startDestination
    ) {
        searchScreen()

        bookmarkScreen()
    }
}
