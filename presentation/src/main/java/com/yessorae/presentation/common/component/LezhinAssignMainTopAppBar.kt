package com.yessorae.presentation.common.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.theme.LezhinAssignmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LezhinAssignMainTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier,
        actions = actions
    )
}

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun LezhinAssignMainTopAppBarPreview() {
    LezhinAssignmentTheme {
        LezhinAssignMainTopAppBar(
            title = "Preview Title",
            actions = {
                TextButton(onClick = {}) {
                    Text(text = "Text Button")
                }
            }
        )
    }
}
