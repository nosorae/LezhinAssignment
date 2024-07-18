package com.yessorae.presentation.screen.bookmark.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yessorae.presentation.R
import com.yessorae.presentation.common.component.LezhinAssignmentImage
import com.yessorae.presentation.screen.bookmark.model.BookmarkImageUi

@Composable
fun BookmarkListItem(
    modifier: Modifier = Modifier,
    bookmarkImageUi: BookmarkImageUi,
    onClickDelete: () -> Unit
) {
    val aspectRatio = bookmarkImageUi.width.toFloat() / bookmarkImageUi.height.toFloat()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            LezhinAssignmentImage(
                imageUrl = bookmarkImageUi.imageUrl,
                thumbnailUrl = bookmarkImageUi.thumbnailUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio)
            )

            IconButton(
                onClick = onClickDelete,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete"
                )
            }
        }

        Text(
            text = stringResource(id = R.string.bookmark_search_with_keyword).format(bookmarkImageUi.keyword),
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1
        )
    }
}
