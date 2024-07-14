package com.yessorae.presentation.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yessorae.presentation.R

@Composable
fun LezhinAssignmentErrorGuide(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.common_error_toast),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.displayMedium
        )
    }
}