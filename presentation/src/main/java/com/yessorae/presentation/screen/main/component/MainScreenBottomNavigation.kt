package com.yessorae.presentation.screen.main.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.yessorae.presentation.R
import com.yessorae.presentation.screen.bookmark.BOOKMARK_ROUTE
import com.yessorae.presentation.screen.saerch.SEARCH_ROUTE

private data class BottomNavigationDestinationInfo(
    val route: String,
    val icon: ImageVector,
    @StringRes
    val title: Int
)

private val screens = listOf(
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

@Composable
fun MainScreenBottomNavigation(
    currentDestination: NavDestination?,
    onNavigateTo: (route: String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        screens.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            val tint = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onBackground
            }

            val title = stringResource(id = screen.title)

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateTo(screen.route) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = title,
                        tint = tint
                    )
                },
                label = {
                    Text(
                        text = title,
                        color = tint
                    )
                }
            )
        }
    }
}
