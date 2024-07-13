package com.yessorae.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yessorae.presentation.screen.main.MainScreen
import com.yessorae.presentation.theme.LezhinAssignmentTheme

@Composable
fun LezhinAssignmentApp(modifier: Modifier = Modifier) {
    LezhinAssignmentTheme {
        MainScreen(modifier = modifier)
    }
}
