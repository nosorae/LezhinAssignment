package com.yessorae.presentation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yessorae.presentation.screen.main.MainScreen
import com.yessorae.presentation.theme.LezhinAssignmentTheme

@Composable
fun LezhinAssignmentApp(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass
) {
    LezhinAssignmentTheme {
        MainScreen(
            modifier = modifier,
            windowSizeClass = windowSizeClass
        )
    }
}
