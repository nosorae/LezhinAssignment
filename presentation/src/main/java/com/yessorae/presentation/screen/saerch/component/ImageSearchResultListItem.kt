package com.yessorae.presentation.screen.saerch.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yessorae.presentation.common.component.LezhinAssignmentImage
import com.yessorae.presentation.screen.saerch.model.ImageUi

@Composable
fun ImageSearchResultListItem(
    modifier: Modifier = Modifier,
    imageUi: ImageUi,
    onClickBookmark: () -> Unit
) {
    val aspectRatio = imageUi.width.toFloat() / imageUi.height.toFloat()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
    ) {
        LezhinAssignmentImage(
            imageUrl = imageUi.imageUrl,
            thumbnailUrl = imageUi.thumbnailUrl,
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = onClickBookmark,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Bookmark",
                tint = if (imageUi.isBookmark) Color.Yellow else Color.LightGray.copy(alpha = 0.9f)
            )
        }
    }
}
