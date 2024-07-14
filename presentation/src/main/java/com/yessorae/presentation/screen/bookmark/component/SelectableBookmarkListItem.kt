package com.yessorae.presentation.screen.bookmark.component

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.yessorae.presentation.screen.bookmark.model.SelectableBookmarkImageUi
import com.yessorae.presentation.screen.saerch.model.ImageUi

@Composable
fun SelectableBookmarkListItem(
    modifier: Modifier = Modifier,
    selectableBookmarkImageUi: SelectableBookmarkImageUi,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val bookmarkImageUi = selectableBookmarkImageUi.bookmarkImageUi
    val aspectRatio = bookmarkImageUi.width.toFloat() / bookmarkImageUi.height.toFloat()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .clickable(onClick = onClick)
            .alpha(if (selectableBookmarkImageUi.selected) 0.5f else 1f)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(bookmarkImageUi.thumbnailUrl)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
