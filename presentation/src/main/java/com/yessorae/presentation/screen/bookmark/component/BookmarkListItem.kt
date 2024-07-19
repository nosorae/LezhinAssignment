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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yessorae.presentation.R
import com.yessorae.presentation.common.component.LezhinAssignmentImage
import com.yessorae.presentation.common.util.DevicePreviews
import com.yessorae.presentation.common.util.ThemePreviews
import com.yessorae.presentation.screen.bookmark.model.BookmarkImageUi
import com.yessorae.presentation.theme.LezhinAssignmentTheme

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
                    .aspectRatio(aspectRatio),
                contentDescription = stringResource(id = R.string.content_description_bookmark_search_result)
            )

            IconButton(
                onClick = onClickDelete,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.content_description_delete_bookmark)
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

@ThemePreviews
@DevicePreviews
@Preview
@Composable
fun BookmarkListItemPreview() {
    val sampleBookmarkImageUi = BookmarkImageUi(
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcrrwZQarWGinKPcFJf4OUumBrwW1CMkhV8Q&s",
        thumbnailUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcrrwZQarWGinKPcFJf4OUumBrwW1CMkhV8Q&s",
        width = 200,
        height = 300,
        keyword = "Sample"
    )
    LezhinAssignmentTheme {
        BookmarkListItem(
            bookmarkImageUi = sampleBookmarkImageUi,
            onClickDelete = {}
        )
    }
}
