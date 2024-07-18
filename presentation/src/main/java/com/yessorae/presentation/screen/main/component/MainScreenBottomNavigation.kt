package com.yessorae.presentation.screen.main.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.screen.main.model.screens

@Composable
fun MainScreenBottomNavigation(
    currentDestination: NavDestination?,
    onNavigateTo: (route: String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        screens.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            val title = stringResource(id = screen.title)

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateTo(screen.route) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = title
                    )
                },
                label = {
                    Text(text = title)
                }
            )
        }
    }
}

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun MainScreenBottomNavigationPreview() {
    MainScreenBottomNavigation(
        currentDestination = null,
        onNavigateTo = {}
    )
}
