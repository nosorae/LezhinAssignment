package com.yessorae.presentation.common.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LezhinAssignmentTextField(
    modifier: Modifier = Modifier,
    hint: String,
    keyword: String,
    onKeywordChanged: (String) -> Unit,
    onClickClearKeyword: () -> Unit
) {
    TextField(
        value = keyword,
        onValueChange = onKeywordChanged,
        modifier = modifier,
        singleLine = true,
        placeholder = { Text(text = hint) },
        trailingIcon = {
            if (keyword.isNotBlank()) {
                IconButton(onClick = onClickClearKeyword) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        }
    )
}