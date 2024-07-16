package com.yessorae.presentation.screen.main.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.sharp.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.yessorae.presentation.R
import com.yessorae.presentation.screen.bookmark.BOOKMARK_ROUTE
import com.yessorae.presentation.screen.saerch.SEARCH_ROUTE

data class BottomNavigationDestinationInfo(
    val route: String,
    val icon: ImageVector,
    @StringRes
    val title: Int
)

val screens = listOf(
    BottomNavigationDestinationInfo(
        route = SEARCH_ROUTE,
        icon = Icons.Rounded.Search,
        title = R.string.screen_title_search
    ),
    BottomNavigationDestinationInfo(
        route = BOOKMARK_ROUTE,
        icon = Icons.Sharp.Star,
        title = R.string.screen_title_bookmark
    )
)
