package com.yessorae.presentation.screen.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yessorae.presentation.screen.main.component.MainScreenBottomNavigation
import com.yessorae.presentation.screen.main.component.MainScreenNavHost
import com.yessorae.presentation.screen.main.component.MainScreenNavigationRail

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass
) {
    val navController = rememberNavController()
    val currentBackstack by navController.currentBackStackEntryAsState()
    val currentDestination by remember {
        derivedStateOf {
            currentBackstack?.destination
        }
    }
    val showBottomNavigation = remember(windowSizeClass) {
        windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
    }
    val showNavRails = remember(showBottomNavigation) {
        showBottomNavigation.not()
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomNavigation) {
                MainScreenBottomNavigation(
                    currentDestination = currentDestination,
                    onNavigateTo = { route -> navController.navigateSingleTopTo(route) }
                )
            }
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (showNavRails) {
                MainScreenNavigationRail(
                    currentDestination = currentDestination,
                    onNavigateTo = { route -> navController.navigateSingleTopTo(route) }
                )
            }
            MainScreenNavHost(
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
