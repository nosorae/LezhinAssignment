package com.yessorae.presentation.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.yessorae.presentation.R
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.theme.LezhinAssignmentTheme

@Composable
fun LezhinAssignmentErrorGuide(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.common_error_message)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun LezhinAssignmentErrorGuidePreview() {
    LezhinAssignmentTheme {
        LezhinAssignmentErrorGuide(modifier = Modifier.fillMaxSize())
    }
}
