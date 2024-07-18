package com.yessorae.presentation.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.theme.LezhinAssignmentTheme

@Composable
fun LezhinAssignmentNormalTextGuide(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center
        )
    }
}

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun LezhinAssignmentNormalTextGuidePreview() {
    LezhinAssignmentTheme {
        LezhinAssignmentNormalTextGuide(
            modifier = Modifier.fillMaxSize(),
            title = "이렇게 저렇게 하라는 안내문구"
        )
    }
}
