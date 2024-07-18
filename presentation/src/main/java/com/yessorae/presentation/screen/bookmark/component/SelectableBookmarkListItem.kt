package com.yessorae.presentation.screen.bookmark.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yessorae.presentation.R
import com.yessorae.presentation.common.component.LezhinAssignmentImage
import com.yessorae.presentation.screen.bookmark.model.SelectableBookmarkImageUi

@Composable
fun SelectableBookmarkListItem(
    modifier: Modifier = Modifier,
    selectableBookmarkImageUi: SelectableBookmarkImageUi,
    onClick: () -> Unit
) {
    val bookmarkImageUi = selectableBookmarkImageUi.bookmarkImageUi
    val aspectRatio = bookmarkImageUi.width.toFloat() / bookmarkImageUi.height.toFloat()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .clickable(onClick = onClick)
            .alpha(if (selectableBookmarkImageUi.selected) 0.5f else 1f),
        contentAlignment = Alignment.Center
    ) {
        LezhinAssignmentImage(
            imageUrl = bookmarkImageUi.imageUrl,
            thumbnailUrl = bookmarkImageUi.thumbnailUrl,
            modifier = Modifier.fillMaxSize(),
            contentDescription = stringResource(id = R.string.content_description_bookmark_multi_select)
        )

        if (selectableBookmarkImageUi.selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(id = R.string.content_description_bookmark_multi_select),
                modifier = Modifier.size(48.dp)
            )
        }
    }
}
