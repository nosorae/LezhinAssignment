package com.yessorae.presentation.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.theme.LezhinAssignmentTheme

@Composable
fun LezhinAssignmentCircularProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
    }
}

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun LezhinAssignmentCircularProgressIndicatorPreview() {
    LezhinAssignmentTheme {
        LezhinAssignmentCircularProgressIndicator(modifier = Modifier.fillMaxSize())
    }
}
